package Z_Practice.Review_Day03;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;

public class JsonPathIntro {

    @BeforeAll
    @Test
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    @Test
    public static void tearDown() {
        reset();
    }

    @DisplayName("Extracting Data from the Object")
    @Test
    public void extractingData () {
        JsonPath jp =
                given().auth().basic("admin" , "admin").pathParam("id" , 25).log().all().
                when().get("/spartans/{id}").
                 then().log().all().extract().jsonPath();
                

        System.out.println("jp = " + jp);
        System.out.println("jp.getInt(\"id\") = " + jp.getInt("id"));
        System.out.println("jp.getInt(\"name\") = " + jp.getString("name"));
        System.out.println("jp.getInt(\"gender\") = " + jp.getString("gender"));
        System.out.println("jp.getInt(\"phone\") = " + jp.getLong("phone"));

        Response response =
                given().auth().basic("admin" , "admin").pathParam("id" , 25).log().all().
                        when().get("/spartans/{id}");

        System.out.println("==============");
        System.out.println("response.toString() = " + response.toString());

        System.out.println("jp.getString(\"Content-Type\") = " + jp.getString("Content-Type"));
        // returns null because "Content-Type" IS NOT PART OF PAYLOAD
    }

    @DisplayName("Get All Spartans")
    @Test
    public void getAllSpartans () {
        JsonPath jp =  given().auth().basic("admin" , "admin").
                        when().get("/spartans").jsonPath();
        List<String> allNames =  jp.getList("name" );
        System.out.println("allNames = " + allNames);
        System.out.println("allNames.size() = " + allNames.size());
    }



    @DisplayName("Get All Phone Numbers")
    @Test
    public void getAllPhoneNumbers () {

        JsonPath jp = given().auth().basic("admin" , "admin").when().get("/spartans").jsonPath();

        System.out.println("jp.getString(\"name[5]\") = " + jp.getString("name[5]"));
        
        List<Long> allNumbers = jp.getList("phone");
        System.out.println("allNumbers = " + allNumbers);
    }

    @DisplayName("Names contains specific letters/filter")
    @Test
    public void filterNames () {
        JsonPath jp = given().auth().basic("admin" , "admin").
                        when().get("/spartans/search?nameContains=ca").jsonPath().prettyPeek();

        List<String> getMatchingNames = jp.getList("content");
        System.out.println("getMatchingNames = " + getMatchingNames);

    }


    @DisplayName("Search and Filtering Test")
    @Test
    public void filterNames2 () {
        JsonPath jp = given().auth().basic("admin", "admin").queryParam("nameContains" , "de").
                        when().get("/spartans/search").jsonPath();

        // Get the name of first guy
        String firstGuy = jp.getString("content[0].name");
        System.out.println("firstGuy = " + firstGuy);
        
        //get the phone of 3rd guy
        //long phoneNumber = jp.getLong("content[2].phone");
        //System.out.println("phoneNumber = " + phoneNumber);
        
        
        List<String> allNames = jp.getList("content.name");
        System.out.println("allNames = " + allNames);
        
        boolean empty = jp.getBoolean("pageable.sort.empty");
        System.out.println("empty = " + empty);
        
    }

    
    @DisplayName("Practice Test")
    @Test
    public void practiceTest () {

        //get the whole name List
        //get the phone of 3rd guy
        //get the name of 7th guy
        
        JsonPath jp = given()
                .auth().basic("admin", "admin").
        when()
                .get("/spartans").jsonPath(); //prettyPeek() ;

        List<String> wholeNameList = jp.getList("name");

        System.out.println("wholeNameList = " + wholeNameList);

        System.out.println("jp.getLong(\"phone[2]\") = " + jp.getLong("phone[2]"));

        System.out.println("jp.getString(\"name[6]\") = " + jp.getString("name[6]"));


       
        
    }













}














