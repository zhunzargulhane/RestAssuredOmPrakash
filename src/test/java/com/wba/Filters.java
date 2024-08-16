package com.wba;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Filters {

    @BeforeClass
    public void initialize() throws FileNotFoundException {
        PrintStream fileOutputStream = new PrintStream("restAssured.log");
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://postman-echo.com").addFilter(new RequestLoggingFilter(fileOutputStream)).addFilter(new ResponseLoggingFilter(fileOutputStream));
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void filters() {

        with().
                get("/get");
    }

    @Test
    public void filtersTOFile() {

        with().
                get("/get");
    }
}
