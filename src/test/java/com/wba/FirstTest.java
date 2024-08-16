package com.wba;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FirstTest {

    @Test
    public void myTest() {
        System.out.print("hello");
        String res = given().log().all().baseUri("https://api.postman.com/").header("X-Api-Key", "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").
                when().log().all().get("workspaces").then().log().all().assertThat().statusCode(200)
                .body("workspaces.name", hasItems("ZhunzarWorkspace", "RestAPI", "Team Workspace"), "workspaces.type", hasItems("team", "personal"), "workspaces[0].name", is(equalTo("My Workspace1"))).extract().response().path("workspaces[0].name");
        ;
        System.out.println(res);


       /* given().log().all().baseUri("https://api.postman.com/").header("X-Api-Key", "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").
                when().log().all().get("workspaces").then().log().all().assertThat().statusCode(200)
                .body("workspaces[0]",contsin) ;*/
        System.out.println(res);

        //System.out.println(res.asString());
       /* JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println(jsonPath.getString("workspaces[0].name"));
        System.out.println(JsonPath.from(res.asString()).get("workspaces[0].name"));
        System.out.println("workspace name "+res.path("workspaces[0].name"));*/
       /* "workspaces.type",
                hasItems("team", "personal"),
                "workspaces[0].name", is(equalTo("My Workspace1")), "workspaces.size()", is(equalTo(6)),
                "workspaces.type",hasItem("personal"),"workspaces.type").*/
    }

    public static void main(String[] args) throws IOException, JSONException {
        String s = "[{\n" +
                "  \"firstName\": \"zhunzar\",\n" +
                "  \"lastName\": \"gulhane\"\n" +
                " }\n" +
                "]";
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.readValue(s, new TypeReference<ArrayNode>() {
        });
        for(JsonNode jsonNode:arrayNode){
            Iterator<String> iterator = jsonNode.fieldNames();
            while(iterator.hasNext()){
                System.out.println(jsonNode.get(iterator.next()));
            }
        }

       /* objectMapper.createArrayNode();
        JSONArray jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                System.out.println(jsonObject.get(iterator.next().toString()));
            }
        }*/
    }
}
