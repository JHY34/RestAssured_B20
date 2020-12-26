package Z_Practice.Review_Day04;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {

    String myToken;

    @BeforeAll
    public static void setUp() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }


    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Library Login Test")
    @Test
    public void libraryLogin () {

        /*
        Librarian1
        email --> librarian69@library
        password --> KNPXrm3S
         */

         myToken = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email" ,"librarian69@library" )
                .formParam("password" ,"KNPXrm3S" ).
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(200)
                .body(is(not(emptyString())))
                .body("token" , not(emptyString())).
        extract()
                .jsonPath().getString("token")
                ;

        System.out.println("myToken = " + myToken);

    }

    @DisplayName("Get Dashboard Stats")
    @Test
    public void getDashboardStats () {
        given()
               // .header("x-library-token" , "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjA4OTQ3NjU4LCJleHAiOjE2MTE1Mzk2NTh9.CnnuxDIo21-8ledY21FoNVeZeeB7waQsR4j4mN7WYJA").
                .header("x-library-token" , myToken).
        when()
                .get("/dashboard_stats").prettyPeek();
    }



}
