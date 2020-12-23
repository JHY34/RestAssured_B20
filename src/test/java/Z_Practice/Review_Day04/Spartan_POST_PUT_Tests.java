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

    @DisplayName("POST A Spartan")
    @Test
    public void addOneSpartan () {

        Faker faker = new Faker();

//        String jsonObject = " {\n" +
//                "            \"name\" : faker.funnyName().name() ,\n" +
//                "            \"gender\" : faker.demographic().sex() ,\n" +
//                "            \"phone\" : faker.number().numberBetween(5000000000L, 999999999L)\n" +
//                "        } " ;


        String name = "Kerem";
        String gender = "Male";
        long phone = 1234561234L;



        //JsonPath  jp =
                given()
                                    .auth().basic("admin" , "admin")
                                    .log().all()
                                    .contentType(ContentType.JSON)
                                    .body("name" )
                                    .
                            when()
                                    .get("/spartans").
                            then()
                                    .log().all()
                                    .statusCode(is(200))
                                    .contentType(ContentType.JSON)
                                    .extract().jsonPath()

                                    ;

      //  System.out.println("jp.getJsonObject(\"Body\") = " + jp.getJsonObject("Body"));


    }

}
