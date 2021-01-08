package Z_Practice.Review_All;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.BeforeAll;
import utility.ConfigurationReader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CRUD {

    private static int id;

    @BeforeAll
    public static void setUp () {
        // http://54.158.11.99:8000/spartans
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath ="/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @Order(1)
    @Test
    public void createOneSpartan () {

        String spartan = "{\n" +
                "            \"name\" : \"John\",\n" +
                "            \"gender\" : \"Male\",\n" +
                "            \"phone\" : 1112223334\n" +
                "        }";

//        {
//            "name" : "John",
//            "gender" : "Male",
//            "phone" : 1112223334
//        }



         id =      given()
                        .auth().basic("admin" , "admin")
                        .log().all()
                      .contentType(ContentType.JSON)
                      .body(spartan).

                when()
                        .post("/spartans").prettyPeek().
                then()
                        .statusCode(201)
              .extract().jsonPath().getInt("data.id")

            ;

        System.out.println("id = " + id);


    }

    @Order(2)
    @Test
    public void getSpartan () {
        given()
                .auth().basic("admin" , "admin")
                .log().all()
                .pathParam("id" , id).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)

                ;
    }


    @Test
    public void updateSpartan () {
        Map<String, Object> newSpartan = new LinkedHashMap<>();
        newSpartan.put("name" , "Engin");
        newSpartan.put("gender" , "Male");
        newSpartan.put("phone" , 2225557778L);

         SpartanPost_POJO sp1 = new SpartanPost_POJO("Can" , "Male" , 3332221114L);

        given()
                .auth().basic("admin" , "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id" , 198)
                .body(sp1).
        when()
                .put("/spartans/{id}").
        then()
                .statusCode(204)
               // .body("" , is(emptyString()))

                ;
    }

    @Test
    public void PostSpartan2 () {
        Map<String, Object> newSpartan = new LinkedHashMap<>();
        newSpartan.put("name" , "Soner");
        newSpartan.put("gender" , "Male");
        newSpartan.put("phone" , 2225557778L);

        SpartanPost_POJO sp1 = new SpartanPost_POJO("Selim" , "Male" , 3332221114L);

        Response response  =    given()
                .auth().basic("admin" , "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id" , 198)
                .body(newSpartan).
                when()
                .get("/spartans/{id}").prettyPeek();


        ;

        De_POJO_Spartan_POST dp = response.body().as(De_POJO_Spartan_POST.class);

        System.out.println("dp = " + dp);
    }




}
