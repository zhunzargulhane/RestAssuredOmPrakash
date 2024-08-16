package com.wba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SerializeListtoJSONArray {

    @BeforeClass
    public void initialize() throws FileNotFoundException {
        PrintStream outputStream = new PrintStream("restassured.log");
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().addFilter(new RequestLoggingFilter(outputStream)).
                addFilter(new ResponseLoggingFilter(outputStream)).
                setBaseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").setContentType("application/json").
                addHeader("x-mock-match-request-body","true");

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json");
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void serialize_using_jackson_json_list() throws JsonProcessingException {
        HashMap<String,String> obj1 = new HashMap();
        obj1.put("firstName","Joe");
        obj1.put("lastName","Smith");

        HashMap<String,String> obj2 = new HashMap();
        obj2.put("firstName","Sally");
        obj2.put("lastName","Brown");

        ArrayList<Object> list = new ArrayList<Object>();
        list.add(obj1);
        list.add(obj2);

        ObjectMapper objectMapper = new ObjectMapper();
        String listStr = objectMapper.writeValueAsString(list);

        Response response = with().body(listStr).post("/post").then().extract().response();
        assertThat(response.path("msg"),is(equalTo("jsonArraysuccess")));
    }

    @Test
    public void serialize_json_using_jackson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode1 = objectMapper.createObjectNode();
        ObjectNode objectNode2 = objectMapper.createObjectNode();
        objectNode1.put("firstName","Joe");
        objectNode1.put("lastName","Smith");

        objectNode2.put("firstName","Sally");
        objectNode2.put("lastName","Brown");

        ArrayNode listObjectNode = objectMapper.createArrayNode();
        listObjectNode.add(objectNode1);
        listObjectNode.add(objectNode2);

        //String listStr = objectMapper.writeValueAsString(listObjectNode);

        Response response = with().body(listObjectNode).post("/post").then().extract().response();
        assertThat(response.<String>path("msg"),is(equalTo("jsonArraysuccess")));
    }
}
