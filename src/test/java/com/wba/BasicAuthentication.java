package com.wba;

import org.testng.annotations.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class BasicAuthentication {
    @Test
    public void base64Credentials(){
        given().auth().basic("","").header("X-API-key","").header("Authorization","Bearer dssdf");
        String credentials = "myUsername:myPassword";
        String s="";
        String base64Encoded = Base64.getEncoder().encodeToString(credentials.getBytes());
        System.out.println(base64Encoded);
        byte[] decodedBytes=Base64.getDecoder().decode(base64Encoded);
        System.out.println(new String(decodedBytes));
    }
}
