package day11;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_XML_ElementAttributes {
    @DisplayName("Test omdbapi xml response and movie info")
    @Test
    public void testMovieAttributes(){
        // search for your favorite movie
        // assert the movie info according to your expected result
        //http://www.omdbapi.com/?t=Wonder Woman 1984&apiKey=5b5d0fe8&r=xml
        Response response =
                given()
                        .baseUri("http://www.omdbapi.com/")
                        .queryParam("apiKey","5b5d0fe8")
                        .queryParam("t","Wonder Woman 1984")
                        .queryParam("r","xml").
                        when()
                        .get().prettyPeek();
        XmlPath xp = response.xmlPath();
    }
}