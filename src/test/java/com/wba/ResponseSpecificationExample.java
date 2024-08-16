package com.wba;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.entity.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

public class ResponseSpecificationExample {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com/").addHeader("X-Api-Key",
                "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void validate_status_code() {
        get("/workspaces");
    }


    @Test
    public void validate_response_body() {
        get("/workspaces").then().body("workspaces[0].name", is(equalTo("My Workspace1")));
    }


}
