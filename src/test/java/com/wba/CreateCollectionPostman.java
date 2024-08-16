package com.wba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wba.pojo.createCollections.*;
import com.wba.pojo.createCollections.basePOJO.CollectionBase;
import com.wba.pojo.createCollections.basePOJO.FolderBase;
import com.wba.pojo.createCollections.basePOJO.RequestBase;
import com.wba.pojo.createCollections.basePOJO.RequestRootBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.hamcrest.Matcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateCollectionPostman {

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com/").addHeader("X-Api-Key",
                "PMAK-65eea86969484300019e46b0-6dd9b170b77e52447854ae19a25acd3f56").setContentType("application/json").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void complexCreateCollectionPOJO() throws JsonProcessingException, JSONException {
        Info info = new Info("Sample CollectionBase", "This is just a sample collection.", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        Body body = new Body("raw", "{\"data\": \"123\"}");
        Header header = new Header("Content-Type", "application/json");

        ArrayList<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        RequestRequest request = new RequestRequest("https://postman-echo.com/post", "POST", headerList, body, "This is a sample POST RequestBase");

        RequestRootRequest item = new RequestRootRequest("Sample POST RequestBase", request);
        ArrayList<RequestRootRequest> itemList = new ArrayList<RequestRootRequest>();
        itemList.add(item);
        FolderRequest mainFolderItem = new FolderRequest("This is a folder", itemList);
        ArrayList<FolderRequest> arrayListMainFolderItem = new ArrayList<FolderRequest>();
        arrayListMainFolderItem.add(mainFolderItem);
        CollectionRequest collectionPost = new CollectionRequest(info, arrayListMainFolderItem);
        RootRequest mainCollection = new RootRequest(collectionPost);
        //RootRequest deserializedCollection = with().body(mainCollection).post("/collections").then().extract().response().as(RootRequest.class);
        String collectionId = with().body(mainCollection).post("/collections").then().extract().response().path("collection.uid");
        RootResponse deserializedCollectionRoot = with().pathParam("Id", collectionId).get("/collections/{Id}").
                then().extract().response().as(RootResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootStr = objectMapper.writeValueAsString(mainCollection);
        String deserializeCollectionRootStr = objectMapper.writeValueAsString(deserializedCollectionRoot);

        JSONAssert.assertEquals(collectionRootStr, deserializeCollectionRootStr,
                new CustomComparator(JSONCompareMode.STRICT_ORDER, new Customization("collection.item[*].item[*].request.url",
                        new ValueMatcher<Object>() {
                            public boolean equal(Object o, Object t1) {
                                return true;
                            }
                        })));

        List<String> urlRequestList = new ArrayList<String>();
        List<String> urlResponseList = new ArrayList<String>();

        for (FolderRequest folderRequest : arrayListMainFolderItem) {
            for (RequestRootRequest requestRootRequest : folderRequest.getItem())
                urlRequestList.add(requestRootRequest.getRequest().getUrl());
            // urlRequestList.add("https://dummy.com");
        }

        for (FolderResponse folderResponse : deserializedCollectionRoot.getCollection().getItem()) {
            for (RequestRootResponse requestRootResponse : folderResponse.getItem()) {
                urlResponseList.add(requestRootResponse.getRequest().getUrl().getRaw());
            }
        }
        System.out.println("Expected data is "+urlRequestList);
        System.out.println("Actual data is "+urlResponseList.toArray());
        assertThat(urlRequestList, containsInAnyOrder(urlResponseList.toArray()));
        assertThat(urlRequestList,contains(urlResponseList.toArray()));
        //assertThat(urlResponseList, contains(urlRequestList));


     /*   assertThat(mainCollection.getCollection().getInfo().getName(),equalTo(deserializedCollection.getCollection().getName()));
        assertThat(deserializedCollection.getCollection().getId(),matchesPattern("^[a-z0-9-]{36}$"));
        assertThat(deserializedCollection.getCollection().getUid(),matchesPattern("^[a-z0-9-]{45}$"));*/

    }

    @Test
    public void simpleCreateCollectionPOJO() throws JsonProcessingException, JSONException {
        ArrayList<FolderRequest> arrayListMainFolderItem = new ArrayList();
        Info info = new Info("Sample CollectionBase23", "This is just a sample collection.", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        CollectionRequest collectionPost = new CollectionRequest(info, arrayListMainFolderItem);
        RootRequest mainCollection = new RootRequest(collectionPost);

        String collectionId = with().body(mainCollection).post("/collections").then().extract().response().path("collection.uid");
        RootResponse deserializedCollectionRoot = with().pathParam("Id", collectionId).get("/collections/{Id}").then().extract().response().as(RootResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootStr = objectMapper.writeValueAsString(mainCollection);
        String deserializeCollectionRootStr = objectMapper.writeValueAsString(deserializedCollectionRoot);

        assertThat(objectMapper.readTree(collectionRootStr),
                equalTo(objectMapper.readTree(deserializeCollectionRootStr)));
    }
}
