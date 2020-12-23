package day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testbase.SpartanAdminTestBase;
import utility.SpartanUtil;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidationPractice extends SpartanAdminTestBase {

    @DisplayName("Testing GET /spartans endpoint structure")
    @Test
    public void testAllSpartanResponseSchema(){

        given()
                .spec(adminReqSpec).
                when()
                .get("/spartans").
                then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("allSpartansSchema.json")) ;

    }

    @DisplayName("Testing POST /spartans endpoint response structure")
    @Test
    public void testPostSpartanResponseSchema(){

        given()
                .spec(adminReqSpec)
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanPOJO_Payload()).
                when()
                .post("/spartans").
                then()
                .body(matchesJsonSchemaInClasspath("postSuccessResponseSchema.json") )
        ;

    }

}