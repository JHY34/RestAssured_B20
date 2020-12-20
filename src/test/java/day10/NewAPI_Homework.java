package day10;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ArticlePOJO;
import utility.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;

public class NewAPI_Homework {

    @DisplayName("Get All Article Author if source id is not null")
    @Test
    public void testGetAllArticleAuthor () {
        
        JsonPath jp =
        given()
                .log().uri()
                .baseUri("http://newsapi.org")
                .basePath("/v2")
                .queryParam("apiKey" , "b4bdf85d74024a26b1d975b9c79b35fd")
                .queryParam("country" , "us").
        when()
                .get("/top-headlines").prettyPeek().jsonPath();
        
        List<String> allAuthorsNoFilter = jp.getList("articles.author");
        System.out.println("allAuthorsNoFilter = " + allAuthorsNoFilter);
        System.out.println("allAuthorsNoFilter.size() = " + allAuthorsNoFilter.size());
        
        
        List<String> allAuthors = jp.getList("articles.findAll{ it.source.id != null }.author");
        System.out.println("allAuthors = " + allAuthors);
        System.out.println("allAuthors.size() = " + allAuthors.size());
        

        // additional requirement -- remove any author with null value
        List<String> allAuthorsWithNoNull =
                jp.getList("articles.findAll{ it.source.id != null && it.author!=null }.author"  ) ;
        System.out.println("allAuthorsWithNoNull = " + allAuthorsWithNoNull);
        System.out.println("allAuthorsWithNoNull.size() = " + allAuthorsWithNoNull.size());

        List<ArticlePOJO> allArticles
                = jp.getList("articles.findAll{ it.source.id != null && it.author!=null }",ArticlePOJO.class) ;
        allArticles.forEach(System.out::println);
                
                
    }


}
