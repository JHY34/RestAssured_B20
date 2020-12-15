package day04;


import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;


public class SpartanUpdatingTest {


    @BeforeAll
    public static void setUp(){
        // baseURI = "http://54.90.101.103:8000";  // Akbar's IP
        baseURI = "http://18.215.150.247:8000";   // My IP
        basePath = "/api" ;
    }



    @AfterAll
    public static void tearDown(){
        reset();
    }



    @DisplayName("Testing PUT /api/spartans/{id} with String body")
    @Test
    public void testUpdatingSingleSpartanWithSingleBody(){

        String updateStrPayload =  "    {\n" +
                                    "        \"name\": \"Christiano\",\n" +
                                    "        \"gender\": \"Male\",\n" +
                                    "        \"phone\": 9876543210\n" +
                                    "    }" ;

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(updateStrPayload).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .header("Date", is(notNullValue()))
                // This is how we can check a header exists by checking the value is not null
                // using notNullValue() matcher
        ;
    }

    @DisplayName("Testing PATCH /api/spartans/{id} with String body")
    @Test
    public void testPartialUpdatingSingleSpartanWithSingleBody(){

        // update the name to Cristiano --> Merih
        // {"name" : "Merih"}

        String patchBody = "{\"name\" : \"Merih\"}";

        given()
                .auth().basic("admin" , "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(patchBody).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                // body for 204 response is always empty
                // we can validate it using emptyString() matcher
                .body(emptyString())

                ;
    }


    @DisplayName("Testing DELETE /api/spartans/{id}")
    @Test
    public void testDeleteSingleSpartan (){

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .pathParam("id", 2).
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .body(is(emptyString()))

                ;
    }




}
