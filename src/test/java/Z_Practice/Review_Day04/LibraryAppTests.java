package Z_Practice.Review_Day04;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import utility.ConfigurationReader;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class LibraryAppTests {

    static String myToken;

    @BeforeAll
    public static void setUp() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }


    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Login the Library App")
    @Test
    public void a_loginLibrary () {

         myToken = given()
                .contentType(ContentType.URLENC)
                .formParam("email" , "librarian69@library")
                .formParam("password" , "KNPXrm3S").log().all().
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(is(200))
                .extract().jsonPath().getString("token");
        
        ;

        System.out.println("myToken = " + myToken);

    }

    @DisplayName("Dashboard_Stats")
    @Test
    public void z_dashboardStats () {
        given()
                .header("x-library-token" , myToken).
        when()
                .get("/dashboard_stats");
    }






}
