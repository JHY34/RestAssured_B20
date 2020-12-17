package day07;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;


public class PatchOneSpartanTest {

    @BeforeAll
    public static void setUp(){
        //RestAssured.filters().add(new AllureRestAssured() ) ;
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
    }


    @AfterAll
    public static void tearDown(){
        reset();
    }


    @DisplayName("Patching 1 data with Java Object")
    @Test
    public void testPath1DataPartialUpdate(){

        // we just want to update the name and phone number
        Map<String , Object> patchBodyMap = new LinkedHashMap<>();
        patchBodyMap.put("name" , "Osman");
        patchBodyMap.put("gender" , "Male");
        patchBodyMap.put("phone" , 5554443332L);

        given()
                .auth().basic("admin" , "admin")
                .pathParam("id" , 100)
                .contentType(ContentType.JSON)
                .body(patchBodyMap).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(204))

                ;
    }

    @DisplayName("Patching 1 data with POJO")
    @Test
    public void testPath1DataPartialUpdateWithPOJO(){
        // we just want to update the name and phone number
        Spartan sp = new Spartan();  //"Sedat" , "" , 9876543210l);

        // it should fail because MAP is a bettr option with minimal effort
        // POJO class need so me handling to ignore empty field values
        // when being serialized

        given()
                .auth().basic("admin","admin")
                .log().all()
                .pathParam("id",100)
                .contentType(ContentType.JSON)
                .body( sp).
                when()
                .patch("/spartans/{id}").
                then()
                .log().all()
                .statusCode(204) ;
    }
}