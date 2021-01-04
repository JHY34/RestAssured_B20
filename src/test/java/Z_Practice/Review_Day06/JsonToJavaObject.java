package Z_Practice.Review_Day06;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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

public class JsonToJavaObject {

    @BeforeAll
    public static void setUp () {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @DisplayName("Get 1 Data with Save Response Json As Java Object")
    @Test
    public void deSerializationExample () {
     JsonPath jp =     given()
                                .auth().basic("admin" , "admin")
                                .pathParam("id" , 25)
                                .log().all().
                        when()
                                .get("/spartans/{id}").
                        then()
                                .statusCode(200)
                                .log().all().extract().jsonPath()
                                ;

        System.out.println("jp.getMap(\"\") = " + jp.getMap(""));

    }



}
