package com.wba;

import io.restassured.RestAssured;
import io.restassured.config.Config;
import io.restassured.config.EncoderConfig;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;



import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class RequestParameter {
    @Test
    public void queryParam() {
        given().log().all().baseUri("https://postman-echo.com").queryParam("foo1", "value1")/*.param("foo1","value1")*/.
                when().log().all().get("/get").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void multipleQueryParam() {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("foo1", "value1");
        queryParams.put("foo2", "value2");
        given().log().all().baseUri("https://postman-echo.com").queryParams(queryParams).
                when().log().all().get("/get").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void multiValueQueryParam() {
        given().log().all().baseUri("https://postman-echo.com").queryParams("foo1", "bar1,bar2,bar3").
                when().log().all().get("/get").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void pathParam() {
        given().log().all().baseUri("https://reqres.in").pathParam("userId", "2").
                when().log().all().get("/api/users/{userId}").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void multipartformData(){
        given().log().all().baseUri("https://postman-echo.com").multiPart("foo1","bar1").multiPart("foo2","bar2").
                when().log().all().post("/post").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void uploadMultipartformData(){
        given().log().all().baseUri("https://postman-echo.com").multiPart("file",new File(System.getProperty("user.dir")+"\\src\\main\\resources\\createWorkspacePayload.json")).
                multiPart("attributes","{\"name\":\"mark\"}","application/json").
                when().log().all().post("/post").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void downloadFile() throws IOException {

        byte[] byteArray = given().log().all().baseUri("https://github.com").
                when().log().all().get("/appium/appium/raw/master/packages/appium/sample-code/apps/ApiDemos-debug.apk").then().log().all().extract().response().asByteArray();

        OutputStream outputStream = new FileOutputStream(new File("newMyfile"));
        outputStream.write(byteArray);
        outputStream.close();

        /*byte[] bytes = given().log().all().baseUri("https://github.com").
                when().log().all().get("/appium/appium/raw/master/packages/appium/sample-code/apps/ApiDemos-debug.apk").then().log().all().extract().response().asByteArray();
        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();*/
    }

    @Test
    public void downloadFileUsingInputStream() throws IOException {
        InputStream inputStream = given().log().all().baseUri("https://github.com").
                when().log().all().get("/appium/appium/raw/master/packages/appium/sample-code/apps/ApiDemos-debug.apk").then().log().all().extract().response().asInputStream();
        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        os.write(bytes);
        os.close();
    }

    @Test
    public void formURLEncoded() {
      given().log().all().baseUri("https://postman-echo.com").config(config().encoderConfig(EncoderConfig.encoderConfig().
                      appendDefaultContentCharsetToContentTypeIfUndefined(false))).
              formParam("key1","value1").formParam("key 2","value 2").
                when().
              log().all().post("/post").then().log().all().assertThat().statusCode(200);

    }


    @Test
    public void validateJSONSchema() {
        given().log().all().baseUri("https://postman-echo.com").
                log().all().get("/get").then().log().all().assertThat().statusCode(200).
        body(matchesJsonSchemaInClasspath("echoGet.json"));
    }

}
