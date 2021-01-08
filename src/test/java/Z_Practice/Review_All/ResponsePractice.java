package Z_Practice.Review_All;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;




import org.junit.jupiter.api.BeforeAll;
import utility.ConfigurationReader;

public class ResponsePractice {

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

    @Test
    public void getResponse() {

       Response response = get("/spartans/198");
        System.out.println("response.jsonPath().getMap(\"\") = " + response.jsonPath().getMap(""));

    }


}
