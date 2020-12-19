package day09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testbase.HR_ORDS_TestBase;

import static io.restassured.RestAssured.* ;

public class ORDS_API_DB_PracticeTest extends HR_ORDS_TestBase {

    @DisplayName("GET /counties/{country_id} Compare REsult with DB")
    @Test
    public void tstResponseMatchDatabaseData () {

        String myCountryID = "AR";
        // send request to /countries/{country_id} for AR 
        // save the result as Country POJO

        Country arPOJO = given()
                .pathParam("country_id" , myCountryID).
        when()
                .get("/countries/{country_id}")
                .as(Country.class) ;
        
        
        // Here is the shorter way of above code
        Country arPOJO1 = get("/countries/{country_id}" , myCountryID).as(Country.class) ;

        System.out.println("arPOJO = " + arPOJO);

    }


}
