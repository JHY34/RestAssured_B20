package Z_Practice.Review_Day05;

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

public class Extract_Practice {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Spartan Search")
    @Test
    public void spartanSearch () {

    JsonPath jp = given()
                            .auth().basic("admin" , "admin")
                            .log().all()
                            .queryParam("gender" , "Male")
                            .queryParam("nameContains" , "ea").
                    when()
                            .get("/spartans/search").
                    then()
                            .statusCode(is(200))
                            .log().body()
                            .body("content[0].name" , is("Earlie"))
                            .body("content[1].phone" , is(8566508311L))
                            .body("numberOfElements" , equalTo(2))
                            .extract().jsonPath()
                            ;
        System.out.println("jp.getBoolean(\"pageable.sorted.unsorted\") = " + jp.getBoolean("pageable.sort.unsorted"));
        System.out.println("jp.getBoolean(\"pageable.sorted.sorted\") = " + jp.getBoolean("pageable.sort.sorted"));
        System.out.println("jp.getBoolean(\"pageable.sorted.empty\") = " + jp.getBoolean("pageable.sort.empty"));

        System.out.println("jp.getList(\"content.name\") = " + jp.getList("content.name"));

    }

    @DisplayName("New Test")
    @Test
    public




}


