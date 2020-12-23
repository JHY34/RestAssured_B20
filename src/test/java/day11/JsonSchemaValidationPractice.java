package day11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testbase.SpartanAdminTestBase;

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

}