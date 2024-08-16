package com.wba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wba.pojo.simple.Echo;
import com.wba.pojo.simple.SimplePOJO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimplePojo {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                setContentType("application/json").addHeader("x-mock-match-request-body", "true").
                log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectContentType("application/json").
                expectStatusCode(200).log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void simplePojo() throws IOException, JSONException {
        SimplePOJO simplePojo = new SimplePOJO();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");
        SimplePOJO deserializePOJO = with().body(simplePojo).when().post("/simplePojo").then().assertThat().statusCode(200).extract().response().as(SimplePOJO.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String serializedData = objectMapper.writeValueAsString(simplePojo);
        String deSerializedData = objectMapper.writeValueAsString(deserializePOJO);
        JSONAssert.assertEquals(serializedData,deSerializedData, JSONCompareMode.STRICT);

        String data = objectMapper.readTree(new File("src\\main\\resources\\echoGet.json")).toPrettyString();
        System.out.println(data);
        Echo[] echo = objectMapper.readValue(new File("src\\main\\resources\\echo.json"),Echo[].class);
        System.out.println(echo[0].getFirstName()+" "+echo[0].getLastName());
       /* echo[0].setFirstName("pranjali");
        echo[0].setLastName("gulhane");*/
        System.out.println(echo[0].getFirstName()+" "+echo[0].getLastName());
        /*with().body(simplePojo).post("/simplePojo").then().assertThat().
                body("key1", equalTo(simplePojo.getKey1()), "key2", equalTo(simplePojo.getKey2()));*/
    }

    @Test
    public void deserializeSimplePojo() throws JsonProcessingException {
        SimplePOJO simplePojo = new SimplePOJO();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");
        SimplePOJO deserializePojo = with().body(simplePojo).post("/simplePojo").then().extract().response().as(SimplePOJO.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedPojoObject = objectMapper.writeValueAsString(simplePojo);
        String actualPojoObject = objectMapper.writeValueAsString(deserializePojo);
        assertThat(objectMapper.readTree(expectedPojoObject),is(equalTo(objectMapper.readTree(actualPojoObject))));
    }
}
