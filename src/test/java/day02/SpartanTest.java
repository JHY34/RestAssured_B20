package day02;
// Below imports will be used always therefore just copy and paste
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


public class SpartanTest {

    @BeforeAll
    public static void setUp() {

        baseURI = "http://100.26.101.158:8000";  // RestAssured.baseURI but static method thats why it is not necessary
        basePath = "/api";    // static that why RestAssured is not needed
        // baseURI + basePath + whatever you provided in http method like get post
        // for example
        // get("/spartans") --> get(baseURI + basePath + "/spartans")

    }


    @AfterAll
    public static void tearDown () {
        RestAssured.reset();
        // resetting the value of baseURI , basePath to original value
        // since reset(); static method even ONLY reset(); will work by itself
    }


    @DisplayName("Testing /api/spartans/ endpoint")
    @Test
    public void testGetAllSpartans () {

        // send a get request to above endpoint
        // save the response
        // try to assert the status code
        // content type header

        Response response = get("/spartans");  // http://100.26.101.158:8000/api is deleted after the @BeforeAll method
        response.prettyPrint();

        assertThat(response.statusCode(), is(200)) ;
        assertThat(response.contentType() , is(ContentType.JSON.toString()));

    }


    @DisplayName("Testing /api/spartans endpoint XML Response")
    @Test
    public void testGetAllSpartanXML(){

        /**
         * given
         *      --- RequestSpecification
         *      used to provide additional information about the request
         *      base url  base path
         *      header , query params , path variable , payload
         *      authentication authorization
         *      logging , cookie
         * when
         *      --- This is where you actually send the request with http method
         *      -- like GET POST PUT DELETE .. with the URL
         *      -- We get Response Object after sending the request
         * then
         *      -- ValidatableResponse
         *      -- validate status code , header , payload , cookie
         *      -- responseTime , structure of the payload  , logging
         */


        given().header("accept" , "application/xml").   // --> RequestSpecification
        when().get("/spartans").  // This is where you actually send the request with http method
                // http://100.26.101.158:8000/api is removed after the @BeforeAll method
        then().statusCode(200).header("Content-Type" , "application/xml");  // ValidatableResponse



        given().accept(ContentType.XML).
        when().get("http://100.26.101.158:8000/api/spartans").
        then().assertThat().statusCode(   is(200) ).contentType(ContentType.XML);



    }





}
