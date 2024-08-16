package com.wba;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wba.pojo.workspace.WorkspaceNestedPOJO;
import com.wba.pojo.workspace.WorkspaceRoot;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class WorkspacePOJO {
    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com/").addHeader("X-Api-Key",
                "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test(dataProvider = "getData")
    public void workspace_serialize_deserialize(String name, String type, String description) {
        WorkspaceNestedPOJO workspaceNestedPOJO = new WorkspaceNestedPOJO();
        workspaceNestedPOJO.setName(name);
        workspaceNestedPOJO.setType(type);
        workspaceNestedPOJO.setDescription(description);

        HashMap<String,String> myHashMap = new HashMap<String, String>();
        workspaceNestedPOJO.setMyHashMap(myHashMap);
        WorkspaceRoot workspaceRoot = new WorkspaceRoot(workspaceNestedPOJO);

        WorkspaceRoot deserializeWorkspaceRoot = with().body(workspaceRoot).post("/workspaces").then().extract().response().as(WorkspaceRoot.class);

        assertThat(workspaceRoot.getWorkspace().getName(), is(equalTo(deserializeWorkspaceRoot.getWorkspace().getName())));
        assertThat(deserializeWorkspaceRoot.getWorkspace().getId(), matchesPattern("^[0-9a-z-]{36}$"));

    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"AlphaWorkspace", "team", "Alpha description"},
                {"BetaWorkspace", "personal", "Beta description"}
        };
    }

}
