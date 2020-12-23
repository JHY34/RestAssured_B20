package Z_Practice.Review_Day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OpenMovieDB {

    @BeforeAll
    public static void setUp () {
        baseURI = "http://www.omdbapi.com";
        basePath = "";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Get Single Movie")
    @Test
    public void getSingleMovie () {

        // http://www.omdbapi.com/?t=Hababam
        // apikey : 429d5914

        JsonPath jp =  given()
                .queryParam("apikey" , "429d5914")
                .queryParam("t" , "Back to the future")
                .accept(ContentType.JSON).
        when()
                .get("").prettyPeek().
        then()
                .statusCode(is (200))
                .contentType(ContentType.JSON).extract().jsonPath();

        System.out.println("jp.getString(\"Title\") = " + jp.getString("Ratings.Value"));
        System.out.println("jp.getString(\"Genre\") = " + jp.getJsonObject("Writer"));
        System.out.println("jp.getString(\"Genre\") = " + jp.getJsonObject("Runtime"));
        System.out.println("jp.getString(\"Genre\") = " + jp.getJsonObject("Director"));
        System.out.println("jp.getString(\"Genre\") = " + jp.getJsonObject("Country"));


    }



}
