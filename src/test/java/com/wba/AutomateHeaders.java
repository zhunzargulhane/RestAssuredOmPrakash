package com.wba;
import static io.restassured.RestAssured.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.*;

public class AutomateHeaders {

    //@Test
    public void multipleHeaders(){
        Header header = new Header("header","value1");
        Header matchHeader = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header,matchHeader);
        /*Map<String,String> headers = new HashMap<String,String>();
        headers.put("header","value1");
        headers.put("x-mock-match-request-headers","header");*/
       /* given().log().all().
                baseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                headers(headers).when().get("/get").then().log().all().assertThat().statusCode(200);*/

    }

    @Test
    public void multiValueHeaders(){
       /* Header header1 = new Header("header","value1");
        Header header2 = new Header("header","value2");
        Headers headers = new Headers(header1,header2);*/

        given().log().all().
                baseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                header("header","value1","value2").when().get("/get").then().log().all().assertThat().statusCode(200);
    }

    //@Test
    public void assertResponseHeaders(){
        Header header = new Header("header","value1");
        Header matchHeader = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header,matchHeader);
        given().log().all().
                baseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                headers(headers).when().get("/get").then().log().all().assertThat().statusCode(200).
                headers("responseHeader","resValue1","X-RateLimit-Limit","120");
    }

    @Test
    public void extractResponseHeaders(){
        Header header = new Header("header","multiple");
        Header matchHeader = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header,matchHeader);
        Headers extractedHeader = given().log().all().
                baseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                headers(headers).when().get("/get").then().log().all().assertThat().statusCode(200).extract().headers();
        for(Header eachHeader:extractedHeader){
            System.out.println("The header name is "+eachHeader.getName()+" and its value = "+eachHeader.getValue());
        }
        System.out.println(extractedHeader.get("responseHeader").getName());
        System.out.println(extractedHeader.get("responseHeader").getValue());
        System.out.println(extractedHeader.getValue("responseHeader"));
        List<String> allValues = extractedHeader.getValues("multiValueHeader");
        System.out.println("The list is "+allValues);
    }

    //@Test
    public void extractMultiValueHeaders(){
        Header header = new Header("header","value1");
        Header matchHeader = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header,matchHeader);
        Headers extractedHeader = given().log().all().
                baseUri("https://39283b30-ec66-4ae8-9f41-93d1f042e210.mock.pstmn.io").
                headers(headers).when().get("/get").then().log().all().assertThat().statusCode(200).extract().headers();

        List<String> listHeaders = extractedHeader.getValues("multiValueHeader");
        for(String eachHeader : listHeaders){
            System.out.println(eachHeader);
        }
    }
}
