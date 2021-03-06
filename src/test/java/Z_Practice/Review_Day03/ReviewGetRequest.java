package Z_Practice.Review_Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;

public class ReviewGetRequest {


    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @DisplayName("Get 1 Spartan")
    @Test
    public void get1Spartan () {

        given()
                .log().all()
                .auth().basic("admin" , "admin")
                .pathParam("id" , 25).
        when()
                .get("/spartans/{id}").prettyPeek().
        then()
                .statusCode(is (200))
                .body("id" , is(25))
                .body("name" , is("Valentin"))
                .body("gender" , is("Male"))
                .body("phone" , is(1536037088))
                ;

    }



}
