package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;

public class RestAssuredIntro {

    @DisplayName("Spartan /api/hello Endpoint Test")
    @Test
    public void TestHello() {

        // This is public IP shard for Spartan2
        // http://100.26.101.158:8000/api/hello

        // My IP is http://18.215.150.247:8000/api/hello

        Response response = get("http://18.215.150.247:8000/api/hello");

        //get status code for this object
        System.out.println("Status code is = " + response.statusCode());
        // prints: Status code is = 200

        assertThat(response.statusCode(), equalTo(200));  // PASSED


        // How to pretty print entire response body
        response.prettyPrint();   // prints: Hello from Sparta

        // prettyPrint() -- print and return the body as String
        String bodyAsStr = response.prettyPrint() ;
        // assertThat the body is Hello from Sparta

        assertThat(bodyAsStr, is("Hello from Sparta"));


        // get the header called ContentType from the response
        System.out.println(response.getHeader("Content-Type"));
        // prints: text/plain;charset=UTF-8

        // There are multiple ways..such as
        System.out.println(response.contentType());
        System.out.println(response.getContentType());
        // All above prints the same thing => text/plain;charset=UTF-8

        assertThat(response.contentType(), equalTo("text/plain;charset=UTF-8"));
        // PASSED

        // assert That Content-Type is text/plain;charset=UTF-8
        assertThat(response.contentType() ,  is("text/plain;charset=UTF-8") );

        // assert That Content-Type startWith text
        assertThat(response.contentType() , startsWith("text") );

        // Easy way to work with Content-type without typing much
        // We can use ContentType Enum from RestAssured to easily get main part content-type
        // ContentType.TEXT -->> text/plain as Enum
        // startWith accept a String object
        // so use toString method to turn ContentType.TEXT to String so we can use it startWith
        assertThat(response.contentType() ,  startsWith( ContentType.TEXT.toString()  ) );
        assertThat(response.contentType() ,  is( not(ContentType.JSON)   ) );



    }

}
