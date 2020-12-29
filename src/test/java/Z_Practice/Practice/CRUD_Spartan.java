package Z_Practice.Practice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CRUD_Spartan {

    @BeforeAll
    public static void setUp() {
        baseURI ="http://http://54.158.11.99:8000";
        basePath ="/api";
    }

    @AfterAll
    public static void tearDown () {
        reset();
    }

    @DisplayName("Create a New Spartan using Map as value")
    @Test
    public void postRequest() {

        Map<String, Object> spartan1 = new HashMap<>();
        spartan1




    }





}
