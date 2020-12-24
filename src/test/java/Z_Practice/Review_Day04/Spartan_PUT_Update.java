package Z_Practice.Review_Day04;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Spartan_PUT_Update {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Get 1 Spartan")
    @Test
    public void get1Spartan () {
        given()
                .auth().basic("admin" , "admin")
                .pathParam("id" , 15).
        when()
                .get("/spartans/{id}").prettyPeek();
    }

    @DisplayName("Update 1 Spartan")
    @Test
    public void update1Spartan () {

    }


}
