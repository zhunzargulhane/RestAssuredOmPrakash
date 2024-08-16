package com.wba;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestPayloadAsJsonArray {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                setContentType("application/json").addHeader("x-mock-match-request-body", "true").
                setConfig(config.encoderConfig(EncoderConfig.encoderConfig().
                        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectContentType("application/json").
                expectStatusCode(200).log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void validate_post_request_jsonArray_list() {
        HashMap<String, String> obj1 = new HashMap<String, String>();
        obj1.put("firstName", "Joe");
        obj1.put("lastName", "Smith");
        HashMap<String, String> obj2 = new HashMap<String, String>();
        obj2.put("firstName", "Sally");
        obj2.put("lastName", "Brown");
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(obj1);
        list.add(obj2);
        Response response = with().body(list).post("/post");
        assertThat(response.<String>path("msg"), is(equalTo("jsonArraysuccess")));
    }
}

