package Z_Practice.Review_Day04;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Spartan_POST_PUT_Tests {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }



    @DisplayName("POST A Spartan")
    @Test
    public void addOneSpartan () {

        Faker faker = new Faker();

        String randomSpartan = " {\n" +
                "            \"name\" : "+faker.funnyName().name()+"  ,\n" +
                "            \"gender\" : "+faker.demographic().sex()+" ,\n" +
                "            \"phone\" : "+faker.number().numberBetween(5000000000L, 999999999L)+"\n" +
                "        } " ;

        String newSpartan = "{\n" +
                "            \"name\" :\"Kandemir\",\n" +
                "                \"gender\" :\"Male\",\n" +
                "                \"phone\" :1233211234\n" +
                "        } ";






        JsonPath  jp =
                given()
                                    .auth().basic("admin" , "admin")
                                    .log().all()
                                    .contentType(ContentType.JSON)
                                    .body(newSpartan )
                                    .
                            when()
                                    .post("/spartans").
                            then()
                                    .log().all()
                                    .statusCode(is(201))
                                    .contentType(ContentType.JSON)
                                    .extract().jsonPath()

                                    ;

       System.out.println("jp.getJsonObject(\"Body\") = " + jp.getJsonObject("data"));

        System.out.println("jp.getString(\"success\") = " + jp.getString("success"));

        System.out.println("jp.getString(\"data.name\") = " + jp.getString("data.name"));


    }


    @DisplayName("Add Another Spartan")
    @Test
    public void AddSingleSpartan () {

        String spartan = "{\n" +
                "        \"name\" :\"Cengiz\",\n" +
                "                \"gender\" :\"Male\",\n" +
                "                \"phone\" :2223334445\n" +
                "        }";

        given()
                .auth().basic("admin" , "admin")
                .contentType(ContentType.JSON)
                .body(spartan).log().all().
        when()
                .post("/spartans").prettyPeek().
        then()
                .contentType(ContentType.JSON)
                .statusCode(is(201))

                ;


    }



































}
