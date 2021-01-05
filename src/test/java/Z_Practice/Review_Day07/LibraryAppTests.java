package Z_Practice.Review_Day07;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

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
