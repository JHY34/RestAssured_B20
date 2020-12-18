package Z_Practice.Review_Day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;


public class Review_Day1 {

    @Test
    public void test1 () {

        Response response = get("http://18.212.117.224:8000/api/hello");

        response.prettyPeek();  // prints everything

        System.out.println("======================");

        response.prettyPrint();   // prints only body which is "Hello from Sparta"

        System.out.println("response.statusCode() = " + response.statusCode());
        // response.statusCode() = 200

        System.out.println("response.contentType() = " + response.contentType());
        //response.contentType() = text/plain;charset=UTF-8




    }


}
