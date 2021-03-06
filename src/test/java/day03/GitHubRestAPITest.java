package day03;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;


public class GitHubRestAPITest {


    // create a test for teting github rest api users end point

    @DisplayName("Test GitHub GET / user")
    @Test
    public void testGitHub(){

        // provide your username as path variable in give section of the chain
        given()
                .pathParam("username", "CybertekSchool").
                when()
                .get("https://api.github.com/users/{username}").
                then()
                .assertThat()
                .statusCode(  is(200)  )
                .contentType(ContentType.JSON)
                .header("server" , "GitHub.com")
                .body("login" , is("CybertekSchool"))

        ;




    }



}
