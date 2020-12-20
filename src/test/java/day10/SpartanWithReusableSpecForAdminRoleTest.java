package day10;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class SpartanWithReusableSpecForAdminRoleTest {

    static RequestSpecification givenSpec;
    static ResponseSpecification thenSpec;

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;

         givenSpec =  given().log().all().auth().basic("admin","admin") ;

         thenSpec = expect().logDetail(LogDetail.ALL)
                    .statusCode(is(200) )
                    .contentType(ContentType.JSON) ;
        // log().all() will not work work with expect()
        // in order to make it work we need to use different method
        // logDetail(LogDetail.ALL) to provide how much we want to log

    }

    @DisplayName("GET /api/spartans/{id} Endpoint Test")
    @Test
    public void testOneSpartan () {
        given()
                .spec(givenSpec).pathParam("id" , 34).
        when()
                .get("/spartans/{id}").
        then()
                .spec(thenSpec)
                ;

        // alternative way , since the data type of givenSpec is already a RequestSpecification
        givenSpec
                .pathParam("id",34).
                when()
                .get("/spartans/{id}").
                then()
                .spec(thenSpec)
        ;
    }


    @DisplayName("GET /api/spartans Endpoint Test")
    @Test
    public void testAllSpartan(){


        given()
                .spec(givenSpec).
        when()
                .get("/spartans").
        then()
                .spec(thenSpec)

                ;

    }



    @DisplayName("POST /api/spartans Endpoint Test")
    @Test
    public void testPostOneData () {

        Spartan randomSpartanPayload = SpartanUtil.getRandomSpartanPOJO_Payload();

        RequestSpecification  postReqSpec =  given().spec(givenSpec)
                                                    .contentType(ContentType.JSON)
                                                    .body(randomSpartanPayload);

        ResponseSpecification postResSpec = expect().statusCode(is(201))
                                                    .contentType(ContentType.JSON)
                                                    .body("success" ,  is("A Spartan is Born!"))
                                                    .body("data.id" , notNullValue() )
                                                    .body("data.name" , is(randomSpartanPayload.getName()) )
                                                    .body("data.gender" , is(randomSpartanPayload.getGender()) )
                                                    .body("data.phone" , is(randomSpartanPayload.getPhone()) )

                                                    ;

        given().spec(postReqSpec).
        when().post("/spartans").
        then().spec(postResSpec);

    }


}
