package com.wba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SerializationUsingJackson {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com/").addHeader("X-Api-Key",
                "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }


    @Test
    public void validate_post_request_as_map_usingJackson() throws JsonProcessingException {
        HashMap<String,Object> mainObject = new HashMap<String, Object>();
        HashMap<String,String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name","ZhunzarWorkspace008");
        nestedObject.put("type","personal");
        nestedObject.put("description","This is your personal items");
        mainObject.put("workspace",nestedObject);

        ObjectMapper objectMapper = new ObjectMapper();
        String mainObjectStr = objectMapper.writeValueAsString(mainObject);

        Response response = with().body(mainObjectStr).post("/workspaces");
        assertThat(response.<String>path("workspace.name"),is(equalTo("ZhunzarWorkspace008")));
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
        assertThat(response.path("workspace.id"),matchesPattern("^[a-zA-Z0-9]{36}$"));
    }

    @Test
    public void serialize_using_jackson_json_list() throws JsonProcessingException {
        HashMap<String,String> obj1 = new HashMap();
        obj1.put("firstName","Joe");
        obj1.put("lastName","Smith");

        HashMap<String,String> obj2 = new HashMap();
        obj2.put("firstName","Sally");
        obj2.put("lastName","Brown");

        ArrayList<Object> list = new ArrayList();
        list.add(obj1);
        list.add(obj2);

        ObjectMapper objectMapper = new ObjectMapper();
        String listStr = objectMapper.writeValueAsString(list);

        Response response = with().body(listStr).post("/post").then().extract().response();
        assertThat(response.<String>path("msg"),is(equalTo("jsonArraysuccess")));
    }
}
