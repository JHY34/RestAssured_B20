package Z_Practice.Review_Day06;

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

public class PostWithCustomObject {

    @BeforeAll
    public static void setUp () {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @DisplayName("Posting from POJO Spartan Class")
    @Test
    public void postingFromPOJO () {

        POJO_Class_Spartan ps = new POJO_Class_Spartan("Sami" , "Male" , 3323454556L);

        given()
                .auth().basic("admin" , "admin")
                .log().all()
                .body(ps)
                .contentType(ContentType.JSON).
        when()
                .post("/spartans").
        then()
                .statusCode(201)
                .log().all()
                .contentType(ContentType.JSON)
                .body("data.name" , is(ps.getName()))
                .body("data.gender" , is(ps.getGender()))
                .body("data.phone" , is(ps.getPhone()));

    }


}
