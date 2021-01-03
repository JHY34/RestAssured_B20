package Z_Practice.Practice;

import Z_Practice.Practice_Util.Practice_Util_Methods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class Practice2 {

    private static Map<String , Object> payload;
    private static  int newID;


    @BeforeAll
    public static void setUp () {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
        payload = Practice_Util_Methods.spartanPayload();

    }

    @AfterAll
    public static void tearDown () {
        reset();
    }



    @DisplayName("POST 1 Spartan")
    @Test
    public void postOneSpartan () {
     newID = given()
                .auth().basic("admin" , "admin")
                .contentType(ContentType.JSON)
                .body(payload)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.name" , is(equalTo(payload.get("name"))))
                .body("data.gender" , is(equalTo(payload.get("gender"))))
                .body("data.phone" , is(equalTo(payload.get("phone"))))
                .extract().jsonPath().getInt("data.id");

        System.out.println("payload = " + payload);

        System.out.println("newID = " + newID);

    }


}
