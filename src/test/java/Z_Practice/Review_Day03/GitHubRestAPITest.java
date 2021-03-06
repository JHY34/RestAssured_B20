package Z_Practice.Review_Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;

public class GitHubRestAPITest {

    @BeforeAll
    public static void setUp() {
       // baseURI = ConfigurationReader.getProperty("spartan.base_url");
       // basePath = "/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @DisplayName("Testing GitHub Account")
    @Test
    public void testingGitHub() {

        // https://api.github.com/users/

        given()
                .log().all()
                .pathParam("username" , "JHY34").
        when()
                .get("https://api.github.com/users/{username}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("login" , equalTo("JHY34"))
                .body("url" , is ("https://api.github.com/users/JHY34"))

                ;
    }


}
