package com.wba;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutomatePost {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com/").addHeader("X-Api-Key",
                "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void validate_post_request_bddStyle(){
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"ee544b26-cef8-40b6-8b3b-3fdd08a869db\",\n" +
                "        \"name\": \"ZhunzarWorkspace1234567\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is your personal, private workspace to play around in. Only you can see the collections and APIs you create here - unless you share them with your team.\"\n" +
                "    }\n" +
                "}        ";
        given().body(payload).when().post("/workspaces").then().assertThat().
                body("workspace.name", is(equalTo("ZhunzarWorkspace1234567")),"workspace.id",matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validate_post_request_nonBDDStyle(){
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"ee544b26-cef8-40b6-8b3b-3fdd08a869db\",\n" +
                "        \"name\": \"ZhunzarWorkspace125\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is your personal, private workspace to play around in. Only you can see the collections and APIs you create here - unless you share them with your team.\"\n" +
                "    }\n" +
                "}        ";
        Response response = with().body(payload).post("/workspaces");
        assertThat(response.<String>path("workspace.name"),is(equalTo("ZhunzarWorkspace125")));
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
    }


    @Test
    public void validate_put_request_nonBDDStyle(){
        String workspaceId = "5d44b701-61c5-4c86-91a1-24ce34adb2d5";
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"ZhunzarWorkspace125Updated\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is updated\"\n" +
                "    }\n" +
                "}        ";
        Response response = with().pathParam("workspaceId",workspaceId).body(payload).put("/workspaces/{workspaceId}");
        assertThat(response.<String>path("workspace.name"),is(equalTo("ZhunzarWorkspace125Updated")));
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
        assertThat(response.<String>path("workspace.id"),is(equalTo(workspaceId)));
    }

    @Test
    public void validate_delete_request_nonBDDStyle(){
        String workspaceId = "e7e88ede-85d1-4905-97ea-8d64f612d218";
        Response response = with().pathParam("workspaceId",workspaceId).delete("/workspaces/(workspaceId)");
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
        assertThat(response.<String>path("workspace.id"),is(equalTo(workspaceId)));
    }

    @Test
    public void validate_post_requestPayload_fromfile(){
        File file = new File(System.getProperty("user.dir")+"//src//main//resources//createWorkspacePayload.json");
        Response response = with().body(file).post("/workspaces");
        assertThat(response.<String>path("workspace.name"),is(equalTo("ZhunzarWorkspace125")));
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validate_post_request_as_map(){
        HashMap<String,Object> mainObject = new HashMap<String, Object>();
        HashMap<String,String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name","ZhunzarWorkspace007");
        nestedObject.put("type","personal");
        nestedObject.put("description","This is your personal items");
        mainObject.put("workspace",nestedObject);
        Response response = with().body(mainObject).post("/workspaces");
        assertThat(response.<String>path("workspace.name"),is(equalTo("ZhunzarWorkspace007")));
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
    }


    @Test(dataProvider="workspaceData")
    public void validate_post_request_as_map1(String workspaceName,String type,String dec){
        HashMap<String,Object> mainObject = new HashMap<String, Object>();
        HashMap<String,String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name",workspaceName);
        nestedObject.put("type",type);
        nestedObject.put("description",dec);
        mainObject.put("workspace",nestedObject);
        Response response = with().body(mainObject).post("/workspaces");
        assertThat(response.path("workspace.name"),is(equalTo("ZhunzarWorkspace007")));
        assertThat(response.path("workspace.id"),matchesPattern("^[a-z0-9-]{36}$"));
    }


    @DataProvider(name="workspaceData")
    public Object[][] getData(){
        return new Object[][]{{"ZhunzarWorkspace007","personal","item"}};
    }
}
