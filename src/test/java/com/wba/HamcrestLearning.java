package com.wba;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;

public class HamcrestLearning {
   // @Test
    public void hamcrestMatchersLearning() {
        Response res = given().config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).log().ifValidationFails().baseUri("https://api.postman.com/").header("X-Api-Key", "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").
                when().get("workspaces").then()/*log().ifValidationFails()*/.assertThat().statusCode(201)
                .body("workspaces.name", containsInAnyOrder("My Workspace1", "ZhunzarWorkspace", "hello Workspace1","Team Workspace", "RestAPI", "ZhunzarWorkspace"),
                        "workspaces.name",
                        is(not(empty())),"workspaces.name",hasSize(6),
                        //"workspaces.name",everyItem(endsWith("my")),
                        "workspaces[0]",hasKey("id"),
                        "workspaces[0]",hasValue("1ffc3857-5189-4f06-8996-99adff6b0ec9"),
                        "workspaces[0]",hasEntry("id","1ffc3857-5189-4f06-8996-99adff6b0ec9"),
                        "workspaces[0]",is(not(equalTo(Collections.EMPTY_MAP))),
                        "workspaces.type[0]",allOf(startsWith("personal"),endsWith("personal")))
                .extract().response();
                res.getBody().prettyPrint();
    }

    @Test
    public void blackListHeaders(){
        Set<String> headers = new HashSet<String>();
        headers.add("X-Api-Key");
        headers.add("Accept");
        given().config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()));
        Headers headers1 = given().when().get("").then().extract().response().headers();
        Header header = headers1.get("");

        for(Header eachHeader:headers1){
            eachHeader.getName();
            eachHeader.getValue();
        }
        Response response = given()/*.config(config.logConfig(LogConfig.logConfig().blacklistHeader("")))*/.baseUri("https://api.postman.com/").header("X-Api-Key", "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").
                when().log().all().get("workspaces").then().assertThat().statusCode(201).log().all()
                .body("workspaces.name", containsInAnyOrder("My Workspace1", "ZhunzarWorkspace", "hello Workspace1","Team Workspace", "RestAPI", "ZhunzarWorkspace"),
                        "workspaces.name",
                        is(not(empty())),"workspaces.name",hasSize(6),
                        //"workspaces.name",everyItem(endsWith("my")),
                        "workspaces[0]",hasKey("id"),
                        "workspaces[0]",hasValue("1ffc3857-5189-4f06-8996-99adff6b0ec9"),
                        "workspaces[0]",hasEntry("id","1ffc3857-5189-4f06-8996-99adff6b0ec9"),
                        "workspaces[0]",is(not(equalTo(Collections.EMPTY_MAP))),
                        "workspaces.type[0]",allOf(startsWith("personal"),endsWith("personal")))
                .extract().response();

    }
}
