package day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.BookCategory;
import pojo.Region;
import testbase.HR_ORDS_TestBase;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDS_API_DB_Test extends HR_ORDS_TestBase {

    @DisplayName("Testing the conenction with query")
    @Test
    public void testDB_Connection () {

        DB_Utility.runQuery("select * from regions");
        DB_Utility.displayAllData();

    }

    /*
    Send a GET /regions/{region_id}  request
    check status code is 200
    save it as Region POJO after status check
    Get your expected result from database query
    select * from regions where region_id = 3;
    save the third row as a map
    verify the data from response match the data from Database
     */


    @DisplayName("Testing GET /regions/{region_id} Data Match Database Data")
    @Test
    public void testRegionDataFromResponseMatchDB_Data(){

        int myID = 3 ;
        Response response = given()
                .pathParam("region_id", myID).
                        when()
                .get("/regions/{region_id}").
                        then()
                .log().body()
                .statusCode(200)
                .extract()
                .response();

        Region r3 = response.as(Region.class) ;
        System.out.println("r3 = " + r3);

        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = "+myID ) ;
        Map<String,String> expectedResultMap =  DB_Utility.getRowMap(1) ;

        System.out.println("expectedResultMap = " + expectedResultMap);
        // verify the actual result from api response match expected database result

    }


}
