package Z_Practice.Practice_Util;

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

public class Spartan_Util {

//    @BeforeAll
//    public static void setUp() {
//        baseURI = ConfigurationReader.getProperty("spartan.base_url");
//        basePath = "/api";
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        reset();
//    }
//
//
//    @DisplayName("Get 1 Spartan Payload")
//    @Test
    public static Map<String, Object> getOneSpartanPayload() {

        Faker faker = new Faker();

        Map<String, Object> spartanPayload = new LinkedHashMap<>();
        spartanPayload.put("name", faker.name().firstName());
        spartanPayload.put("gender", faker.demographic().sex());
        spartanPayload.put("phone", faker.number().numberBetween(3_000_000_000L, 9_999_999_999L));

        return spartanPayload;

    }

    public static void main(String[] args) {
        System.out.println(getOneSpartanPayload());
    }
}
