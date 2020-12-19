package day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

public class Juit5_ParameterizedTest {

    @ParameterizedTest  // when you say @ParameterizedTest you need to add source as seen below
    @ValueSource (ints = {5,6,7,8,9,10})   // each number is injected every test -> 5 test 1, 6 test2 ....
    public void test1 (int myNumber) {

        System.out.println("myNumber = " + myNumber);
        // assert 5,6,7,8,9,10  all less than 10
        assertTrue(myNumber <=100) ;
        // all PASSED
    }
    
    //using CSV file as source for parameterized test
    @ParameterizedTest
    @CsvFileSource (resources = "/zipcode.csv" , numLinesToSkip = 1)
    public void test2 (String zip) {
        System.out.println("zip = " + zip);
        // sending request to zipcode endpoint here
        // api.zippopotam.us/us/90210
        // api.zippopotam.us/us/{zipcode}
        // baseurl : api.zippopotam.us
        // endpoint : /us/{zipcode}
        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode" , zip ).
        when()
                .get("/us/{zipcode}").
        then()
                .statusCode(is (200))

                ;
    }


    @ParameterizedTest
    @CsvFileSource (resources = "/country_zip.csv" , numLinesToSkip = 1)
    public void testCountryZipCodeCombo (String csvCountry , int csvZip) {

        given()
                .log().all()
                .baseUri("https://api.zippopotam.us")
                .pathParam("country" , csvCountry)
                .pathParam("zipcode" , csvZip).
        when()
                .get("/{country}/{zipcode}").
        then()
                .statusCode(200);

        // ALL 7 tests works very well
    }




}
