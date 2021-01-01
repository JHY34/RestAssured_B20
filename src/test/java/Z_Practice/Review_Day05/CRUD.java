package Z_Practice.Review_Day05;

import Z_Practice.Practice_Util.Spartan_Util;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import utility.ConfigurationReader;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CRUD {

    public static Map<String , Object> payload ;

        @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
        payload = Spartan_Util.getOneSpartanPayload();
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    @DisplayName("POST 1 Spartan")
    @Test
    public void postOneSpartan () {
            given()
                    .auth().basic("admin" , "admin")
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(payload).
            when()
                    .post("/spartans").
            then()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .statusCode(201)
                    .body("data.name"  , is (equalTo(payload.get("name"))))
                    .body("data.gender"  , is (equalTo(payload.get("gender"))))
                    .body("data.phone"  , is (equalTo(payload.get("phone"))))
                    ;
    }
}
