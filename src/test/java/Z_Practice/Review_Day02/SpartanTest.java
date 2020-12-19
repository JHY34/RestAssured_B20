package Z_Practice.Review_Day02;

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

public class SpartanTest {

    @BeforeAll
    public static void setUp () {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Get 1 Spartan")
    @Test
    public void getOneSpartan () {

    Response response    =
            given()
                .log().all()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username") , ConfigurationReader.getProperty("spartan.admin.password"))
                .pathParam("id" , 15).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("name" , is("Meta"))
                .body("gender" , is("Female"))
                .body("id" , is(15))
                .body("phone" , is(1938695106))
                .extract().response()
            ;

        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getSessionId() = " + response.getSessionId());

    }


    @DisplayName("Get All Spartans")
    @Test
    public void getAllSpartans () {
        given()
                .log().all()
                .auth().basic("admin" , "admin")
                .accept(ContentType.XML)
                .
        when()
                .get("/spartans").prettyPeek().
        then()
                .log().all()
                .contentType(ContentType.XML)
                .statusCode(is (200));


    }

}


















