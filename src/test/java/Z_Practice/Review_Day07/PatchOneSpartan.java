package Z_Practice.Review_Day07;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PatchOneSpartan {

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
        given()
                .auth().basic("admin" , "admin")
                .log().all()
                .pathParam("id" , 44).
        when()
                .get("/spartans/{id}").prettyPeek();
    }


    @DisplayName("Patch 1 Spartan")
    @Test
    public void patchOneSpartan () {
        String update = "{\n" +
                "            \"name\" : \"Ali\"\n" +
                "        }";

//        {
//            "name" : "Ali"
//        }

        given()
                .auth().basic("admin" , "admin")
                .log().all()
                .pathParam("id" , 44)
                .body(update)
                .contentType(ContentType.JSON).
        when()
                .patch("/spartans/{id}").prettyPeek();
    }

}
