package Z_Practice.Review_Day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetSpartans {

    @BeforeAll
    public static void setUp () {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }


    @DisplayName("Get All Spartans")
    @Test
    public void getSpartans () {
        Response jp = given().auth().basic("admin", "admin").when().get("/spartans");
        jp.prettyPeek();
        
        List<String> allNames = new LinkedList<>();
        allNames = jp.jsonPath().getList("name");
        System.out.println("allNames = " + allNames);
        allNames.forEach(System.out::println);
       
    }

    @DisplayName("Get One Spartan")
    @Test
    public void getOneSpartan () {
        Map<Object, Object> jp = given()
                                        .auth().basic("admin", "admin")
                                        .log().all()
                                        .pathParam("id", 34).
                                  when()
                                        .get("/spartans/{id}").
                                  then()
                                        .statusCode(200)
                                        .log().all()
                                  .extract().jsonPath().getMap("");

        System.out.println("jp = " + jp);





    }


}
