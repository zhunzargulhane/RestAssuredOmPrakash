package com.wba;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class RequestSpecLearning {
   //RequestSpecification requestSpecification;

    @BeforeClass
    public void initialize() {
        /*requestSpecification = with().baseUri("https://api.postman.com/").
                header("X-Api-Key", "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").log().all();*/
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com/").addHeader("X-Api-Key",
                "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification=requestSpecBuilder.build();
    }

    @Test
    public void testDuplicateBaseUri(){
        Header header = new Header("header","value1");
        Header matchHeader = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header,matchHeader);
        given(requestSpecification).baseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").headers(headers).when().get("/get").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void queryRequestSpec(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());
        System.out.println(queryableRequestSpecification.getContentType());
    }

    @Test
    public void withoutANyInfoExecuteget(){
        given().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void requestSpec() {

        Response response = /*given(requestSpecification).*/get("workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void requestSpecAnotherWay() {
        Response response = /*given(requestSpecification).*/get("workspaces").then().assertThat().statusCode(200).extract().response();
        assertThat(response.path("workspaces[0].name").toString(), is(equalTo("My Workspace1")));
    }

    @Test
    public void requestSpecBuilderMethod() {
        RequestSpecification requestSpecBuilder = new RequestSpecBuilder().setContentType("").setBaseUri("").build();

        /*given().spec(requestSpecification).*/get("workspaces").then().assertThat().statusCode(200).extract().response();
    }

    @Test
    public void extractRequestSpecs(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        System.out.println("dfdfgr  "+queryableRequestSpecification.getBaseUri());
        System.out.println("dfdfgr  "+queryableRequestSpecification.getHeaders());
    }
}
