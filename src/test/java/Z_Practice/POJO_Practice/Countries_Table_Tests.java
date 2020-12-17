package Z_Practice.POJO_Practice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import pojo.BookCategory;
import pojo.Region;
import utility.ConfigurationReader;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Countries_Table_Tests {

    @BeforeAll
    public static void setUp () {
        baseURI = "http://18.212.117.224:1000";
        basePath = "/ords/hr";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @DisplayName("Test the 2nd Country is Australia")
    @Test
    public void testOneCountry () {
        Response response =  given()
                .log().all()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username") , ConfigurationReader.getProperty("spartan.admin.password"))
                .pathParam("country_id" , "AU")
                .contentType(ContentType.JSON).
        when()
                .get("/countries/{country_id}").prettyPeek();
//        then()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .statusCode(is (200))
//                .body("country_id" , is ("AU"));

       JsonPath jp = response.jsonPath();

        System.out.println("jp.getInt(\"Region_id\") = " + jp.getString("country_name"));




    }


}
