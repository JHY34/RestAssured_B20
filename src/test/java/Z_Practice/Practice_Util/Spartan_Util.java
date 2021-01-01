package Z_Practice.Practice_Util;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import utility.ConfigurationReader;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Spartan_Util {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Create 1 Spartan")
    @Test
    public void createOneSpartan () {

        Faker faker = new Faker();

        String randomSpartan = "{\n" +
                "            \"name\" : faker.funnyName().name();\n" +
                "            \"gender\" : faker.demographic().sex();\n" +
                "            \"phone\" : faker.number().numberBetween(3_000_000_000L , 9_999_999_999L);\n" +
                "        }";

//        {
//            "name" : faker.funnyName().name();
//            "gender" : faker.demographic().sex();
//            "phone" : faker.number().numberBetween(3_000_000_000L , 9_999_999_999L);
//        }


        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(randomSpartan).
        when()
                .post("/spartans").
        then()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }


}
