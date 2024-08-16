package gmailAPI;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import static io.restassured.RestAssured.*;

import io.restassured.config.SessionConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserProfile {
    String access_token = "ya29.a0Ad52N39k_Uk4CLkpIR7NANPaTw87JGLXG_sEniajWjGFwnrTl9829_UnL-MmoN3uWuB0DTfVvXVwXzccHK-b2w5nkAls6_XVqyCiIfv26RKeenc6QCEZTqUpeXTqVN7ykPU61Vh8Jt1euYTV074Et12lpxMiuGpibAg0_gaCgYKAR8SARASFQHGX2MiYh5VRTDzAQWt0DJRyf8lQw0173";

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecification = requestSpecBuilder.setBaseUri("https://gmail.googleapis.com").
                setContentType("application/json").
                addHeader("Authorization", "Bearer " + access_token).log(LogDetail.ALL).build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder./*expectStatusCode(200).*/
                expectContentType("application/json").log(LogDetail.ALL).build();

    }

    @Test
    public void getUserProfile() {
        given().basePath("/gmail/v1").pathParam("userId", "zhunzargulhane11@gmail.com").get("/users/{userId}/profile");
    }

    @Test
    public void sendEmailAPI() {
        String msg = "From: zhunzargulhane4@gmail.com\n" +
                "To: zhunzargulhane11@gmail.com\n" +
                "Subject: Test\n" +
                "\n" +
                "sending from gmail api zhunzar\n";
        String base64EncodedData = Base64.getUrlEncoder().encodeToString(msg.getBytes());
        base64EncodedData = "{\n" +
                "   \"raw\":" + "\"" + base64EncodedData + "\"\n" +
                "}\n";
      /*  HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("raw",base64EncodedData);*/
        given().basePath("/gmail/v1").pathParam("userId", "zhunzargulhane11@gmail.com").body(base64EncodedData).
                post("/users/{userId}/messages/send");
    }

    @Test
    public void deleteEmailAPI() {
        String msg = "From: zhunzargulhane4@gmail.com\n" +
                "To: zhunzargulhane11@gmail.com\n" +
                "Subject: Test\n" +
                "\n" +
                "deleting one more from gmail api zhunzar\n";
        String base64EncodedData = Base64.getUrlEncoder().encodeToString(msg.getBytes());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("raw", base64EncodedData);
        String idOfEmail = given().basePath("/gmail/v1").pathParam("userId", "zhunzargulhane11@gmail.com").body(hashMap).
                post("/users/{userId}/messages/send").then().extract().path("id");
        given().basePath("/gmail/v1").pathParams("userId", "zhunzargulhane11@gmail.com", "id", idOfEmail).delete("/users/{userId}/messages/{id}").then().assertThat().statusLine("HTTP/1.1 204 No Content").statusCode(204);
    }

    @Test
    public void getEmailAPI() {
        String msg = "From: zhunzargulhane4@gmail.com\n" +
                "To: zhunzargulhane11@gmail.com\n" +
                "Subject: Test\n" +
                "\n" +
                "deleting one more from gmail api zhunzar\n";
        String base64EncodedData = Base64.getUrlEncoder().encodeToString(msg.getBytes());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("raw", base64EncodedData);
        String idOfEmail = given().basePath("/gmail/v1").pathParam("userId", "zhunzargulhane11@gmail.com").body(hashMap).
                post("/users/{userId}/messages/send").then().extract().path("id");
        given().basePath("/gmail/v1").pathParams("userId", "zhunzargulhane11@gmail.com", "id", idOfEmail).get("/users/{userId}/messages/{id}").then().assertThat().statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void getListOfEmailAPI() {
        /*given().basePath("/gmail/v1").queryParams("maxResults",200,"pageToken",1,"includeSpamTrash",true).pathParam("userId", "zhunzargulhane11@gmail.com").get("/users/{userId}/messages");
        RestAssured.config().sessionConfig(new SessionConfig().sessionIdName(""));
        SessionFilter filter = new SessionFilter();
        given().auth().form("username","password",new FormAuthConfig("","","").withAdditionalField("_csrf"));
        given().sessionId(filter.getSessionId()).when().get("/profile/index").then().assertThat().statusCode(200);
*/

       // Cookie cookie = new Cookie.Builder("","")..build();
        SessionFilter filter = new SessionFilter();
        given().auth().form("","",new FormAuthConfig("/sign","usernae",
                "password").withAdditionalField("_csrf")).filter(filter).when().get("/login").then().statusCode(200);
    //    given().cookie(cookie);

        RestAssured.config().sessionConfig(new SessionConfig().sessionIdName(""));
        given().sessionId("filter.getSessionId()").when().get("");
        given().cookie("JSESSIONID",filter.getSessionId());
        Cookie cookie = new Cookie.Builder("JSESSIONID","filter.getSessionId()").
                setSecured(true).setHttpOnly(true).setComment("mycookie").build();
        Cookie cookie1 = new Cookie.Builder("JSESSIONID","filter.getSessionId()").
                setSecured(true).setHttpOnly(true).setComment("mycookie").build();
        Cookies cookies = new Cookies(cookie,cookie1);
        Response response = given().cookies("cookie","cookie1","","").when().get("").then().extract().response();
        System.out.println(response.getCookie("JSESSIONID"));
        response.getDetailedCookie("JSESSIONID");

        Map<String,String> cookieMap = response.getCookies();
        for(Map.Entry<String,String> cookieEntry: cookieMap.entrySet()){
            System.out.print(cookieEntry.getKey()+" "+cookieEntry.getValue());
        }
        //This print only the key name and its value not the additional attributes
        Cookies cookies1 = response.getDetailedCookies();
        List<Cookie> cookieList = cookies1.asList();
        for(Cookie cookie2:cookieList){
            System.out.println(cookie2.getName()+cookie2.getValue());
        }
    }
}
