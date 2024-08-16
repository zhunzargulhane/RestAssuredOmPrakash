package com.wba;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wba.pojo.assignment.Address;
import com.wba.pojo.assignment.Geo;
import com.wba.pojo.assignment.Root;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssignmentClassSection24 {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(201).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void validate_request_pojo_status_id() throws JsonProcessingException {
        Geo geo = new Geo("-37.3159", "81.1496");
        Address address = new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", geo);
        Root root = new Root("Leanne Graham", "Bret", "Sincere@april.biz", address);
        Response response = with().body(root).post("/users").then().extract().response();
        Root deserializedRoot = response.then().extract().response().as(Root.class);
        assertThat(deserializedRoot.getId(), notNullValue());
        ObjectMapper objectMapper = new ObjectMapper();
        String resp = objectMapper.writeValueAsString(deserializedRoot);
        assertThat(response.getBody().prettyPrint().contains("id"),is(equalTo(true)));
        assertThat(resp.contains("id"),is(equalTo(true)));
    }
}
