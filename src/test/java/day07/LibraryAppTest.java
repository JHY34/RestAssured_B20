package day07;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class LibraryAppTest {

    private static String myToken ;

    @BeforeAll
    public static void setUp(){

        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1" ;
    }


    @AfterAll
    public static void tearDown(){
        reset();
    }


    @DisplayName("1. Testing POST /login Endpoint")
    @Test
    public void testLogin () {

        /*
        Librarian1  email   	    librarian69@library
        Librarian1  password		KNPXrm3S
         */

     myToken =
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email" , "librarian69@library")
                .formParam("password" , "KNPXrm3S").
        when()
                .post("/login").
        then()
                .log().all()
                .assertThat()
                .statusCode(is (200))
                .contentType(ContentType.JSON)
                .body("token" , is(not(emptyString())))
                .extract()
                .jsonPath().getString("token")
                ;
        System.out.println("myToken = " + myToken);
        // by saving the response at the end we will able to print it out and completing the test seen above by one single shot.

        // How to extract some data out of response object
        // after doing validation in then section
        // without breaking the chain  --> use extract() method that return
    
    }


    @DisplayName("2. Testing GET /dashboard_stats Endpoint")
    @Test
    public void z_testDashboard_stats(){  // given this name to run thi test after the login test
        // because test order happens according to the alphabetical test name order

        // this is how we provide header    .header()

        given()
                .log().all()
                .header("x-library-token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjA3ODExMzI0LCJleHAiOjE2MTA0MDMzMjR9.a8YIG99qUhJK0IjbQJEJN69utgw_eMclIGlJXybHD_k").
                // instead of above long token we need to use myToken variable but somehow didnt work :(
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)

                ;
    }

    @DisplayName("3. Save the result of Get Dashboard Stat as Map Object")
    @Test
    public void testGetDashBoardStatAsMap(){
        
        /*
        {
    "book_count": "1162",
    "borrowed_books": "650",
    "users": "7411"
    }
         */
        
        JsonPath jp =   given()
                                .log().all()
                                .header("x-library-token" , myToken).
                        when()
                                .get("/dashboard_stats")
                                .jsonPath();
        // get the response as Map
        Map<String ,  Object> responseJsonAsMap = jp.getMap("");
        System.out.println("responseJsonAsMap = " + responseJsonAsMap);
        
    }


    // create a utility class LibraryUtility
    // create a static method called getToken(environment, username, password)



}