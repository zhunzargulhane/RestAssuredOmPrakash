package com.wba;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ComplexeJsonPayload {
    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").setContentType("application/json")
                .addHeader("x-mock-match-request-body", "true").log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_payload_complex_json() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(9);
        HashMap<String, Object> batter1 = new HashMap();
        batter1.put("id", list);
        batter1.put("type", "Chocolate");
        HashMap<String, String> batter0 = new HashMap();
        batter0.put("id", "1001");
        batter0.put("type", "Regular");
        List<Object> batterList = new ArrayList();
        batterList.add(batter0);
        batterList.add(batter1);

        HashMap<String, Object> batterHashMap = new HashMap<String, Object>();
        batterHashMap.put("batter", batterList);

        HashMap<String, Object> parentHashMap = new HashMap<String, Object>();
        parentHashMap.put("id", "0001");
        parentHashMap.put("type", "donut");
        parentHashMap.put("name", "Cake");
        parentHashMap.put("ppu", 0.55);
        parentHashMap.put("batters", batterHashMap);
        ArrayList<String> toppingTypeFirstList = new ArrayList<String>();
        toppingTypeFirstList.add("type1");
        toppingTypeFirstList.add("type2");


        HashMap<String, Object> toppingTypeZeroObject = new HashMap<String, Object>();
        toppingTypeZeroObject.put("type", "None");
        toppingTypeZeroObject.put("id", "5001");

        HashMap<String, Object> toppingTypeFirstObject = new HashMap<String, Object>();
        toppingTypeFirstObject.put("type", toppingTypeFirstList);
        toppingTypeFirstObject.put("id", "5002");


        ArrayList<Object> toppingsList = new ArrayList<Object>();
        toppingsList.add(toppingTypeZeroObject);
        toppingsList.add(toppingTypeFirstObject);

        parentHashMap.put("topping", toppingsList);
        Response response = with().body(parentHashMap).post("/postComplexJSON");
        assertThat(response.<String>path("msg"), is(equalTo("complexJsonSuccess")));
    }

    @Test
    public void validate_post_request_payload_assignment_complex_json() {
        ArrayList<Integer> rgbaZeroList = new ArrayList<Integer>();
        rgbaZeroList.add(255);
        rgbaZeroList.add(255);
        rgbaZeroList.add(255);
        rgbaZeroList.add(1);

        HashMap<String,Object> rgbaZeroMap = new HashMap<String, Object>();
        rgbaZeroMap.put("rgba",rgbaZeroList);
        rgbaZeroMap.put("hex","#000");

        HashMap<String,Object> colorsZeroMap = new HashMap<String, Object>();
        colorsZeroMap.put("color","black");
        colorsZeroMap.put("category","hue");
        colorsZeroMap.put("type","primary");
        colorsZeroMap.put("code",rgbaZeroMap);

        ArrayList<Integer> rgbaFirstList = new ArrayList<Integer>();
        rgbaFirstList.add(0);
        rgbaFirstList.add(0);
        rgbaFirstList.add(0);
        rgbaFirstList.add(1);

        HashMap<String,Object> rgbaFirstMap = new HashMap<String, Object>();
        rgbaFirstMap.put("rgba",rgbaFirstList);
        rgbaFirstMap.put("hex","#FFF");

        HashMap<String,Object> colorsFirstMap = new HashMap<String, Object>();
        colorsFirstMap.put("color","white");
        colorsFirstMap.put("category","value");
        colorsFirstMap.put("code",rgbaFirstMap);

        ArrayList<Object> colors = new ArrayList<Object>();
        colors.add(colorsZeroMap);
        colors.add(colorsFirstMap);

        HashMap<String,Object> colorsMaps = new HashMap<String, Object>();
        colorsMaps.put("colors",colors);

        Response response = with().body(colorsMaps).post("/postComplexJSONAssignment");
        assertThat(response.<String>path("msg"),is(equalTo("complexAssignmentSuccess")));
    }
}


