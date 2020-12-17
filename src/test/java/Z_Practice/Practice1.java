package Z_Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


public class Practice1 {

   @BeforeAll
    public static void setUp() {
       baseURI = "http://18.212.117.224:8000";
       basePath = "/api";
   }

   @AfterAll
    public static void tearDown () {
       reset();
   }

   @DisplayName("Get 1 Spartan /spartan")
    @Test
    public void GetSpartan() {
       given()
               .log().all()
               .auth().basic("admin", "admin").
       when()
               .get("/spartans").
       then()
               .log().all()
               .statusCode(is (200))
               .contentType(ContentType.JSON)
               .time(is(lessThan(3000L)))

               ;
   }

   @DisplayName("Post 1 Spartan")
    @Test
    public void Post1Spartan() {

       String newValue = "{\n" +
               "    \"name\": \"Faruk\",\n" +
               "    \"gender\": \"Male\",\n" +
               "    \"phone\": 4541117370\n" +
               "}";

       given()
               .log().all()
               .auth().basic("admin" , "admin")
               .contentType(ContentType.JSON)
               .body(newValue).
       when()
               .post("/spartans").
       then()
               .log().all()
               .statusCode(is(201))
                .contentType(ContentType.JSON)
               .body("success" , is("A Spartan is Born!"))
               .body("data.name" , is("Faruk"))
               .body("data.phone" , is (4541117370L))

               ;
       
   }
   
   @DisplayName("Post by Map")
    @Test
    public void PostByMap () {
       Map<String, Object> map = new LinkedHashMap<>();
       map.put("name" , "Salih");
       map.put("gender" , "Male");
       map.put("phone" , 1112223334);

       System.out.println("map = " + map);

       given()
               .log().all()
               .auth().basic("admin" , "admin")
               .contentType(ContentType.JSON)
               .body(map).
       when()
               .post("/spartans").
       then()
               .log().all()
               .contentType(ContentType.JSON)
               .statusCode(is(201))
               .body("success", is("A Spartan is Born!") )
               .body("data.name", is("Salih") )
               .body("data.gender", is("Male") )
               .body("data.phone", is(1112223334) )

       ;

   }



   @DisplayName("Post By External File")
    @Test
    public void PostByExternalFile () {

       File newFile = new File("jsonObjects.json");

       given()
               .log().all()
               .auth().basic("admin" , "admin")
               .contentType(ContentType.JSON)
               .body(newFile).
       when()
               .post("/spartans").

      then()
               .log().all()
               .statusCode(is(201))
               .time(is(lessThan(3000L)))
               .contentType(ContentType.JSON)
//               .body("success" , is("A Spartan is Born!"))
//                .body("data.name" , is("Jennifer Garcia"))
//                .body("data.gender" , is("Female"))
//                .body("data.phone" , is(1116667779))

       ;


   }


   @DisplayName("Update 1 Spartan")
    @Test
    public void updateSpartan () {

       String update = "{\n" +
               "           \"name\" : \"Merih\" ,\n" +
               "           \"gender\" : \"Male\" ,\n" +
               "           \"phone\" : 3334445556\n" +
               "       }" ;

      /* {
           "name" : "Merih" ,
           "gender" : "Male" ,
           "phone" : 3334445556
       }*/

      given()
              .log().all()
              .auth().basic("admin" , "admin")
              .contentType(ContentType.JSON)
              .pathParam("id" , 15)
              .body(update).
      when()
              .put("/spartans/{id}").
      then()
              .log().all()
      .statusCode(is(204))

       ;

   }


   @DisplayName("Patch Spartan")
    @Test
    public void patchSpartan() {
       String partialUpdate = "{\"name\" : \"Demir\"}";


       given()
               .log().all()
               .auth().basic("admin", "admin")
               .contentType(ContentType.JSON)
               .pathParam("id" , 15)
               .body(partialUpdate).
       when()
               .patch("/spartans/{id}").
       then()
               .log().all()
               .statusCode(is(204))
               .body(emptyString())


               ;

   }


   @DisplayName("Delete Spartan")
    @Test
    public void deleteSpartan() {

       given()
               .log().all()
               .auth().basic("admin" , "admin")
               .pathParam("id" , 14).
       when()
                .delete("/spartans/{id}").
       then()
                .log().all()
               .statusCode(is (204))


               ;

   }
   
   
   @Test
    public void practice () {
       Response response = when().get("/employees");
       System.out.println("response = " + response);
   }











}




