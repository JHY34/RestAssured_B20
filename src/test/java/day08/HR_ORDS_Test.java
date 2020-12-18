package day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.BookCategory;
import pojo.Country;
import pojo.Region;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class HR_ORDS_Test {

    //http://54.90.101.103:1000/ords/hr/countries/AR
    @BeforeAll
    public static void setUp(){
        baseURI = "http://18.212.117.224:1000";
        basePath = "/ords/hr" ;
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test GET /countries/{country_id} to POJO")
    @Test
    public void testCountryResponseToPOJO(){

        // Response response = get("/countries/{country_id}" , "AR");
        // This is the shortes way to assign to the "response"
        // The other way is below

        Response response = given()
                .pathParam("country_id","AR").
                        when()
                .get("/countries/{country_id}").prettyPeek();
        Country ar = response.as(Country.class) ;
        System.out.println("Argentina = " + ar);
        Country ar1 = response.jsonPath().getObject("",Country.class);
        System.out.println("Argentina with jsonPath = " + ar1);
    }
}