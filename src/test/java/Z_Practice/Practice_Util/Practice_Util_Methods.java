package Z_Practice.Practice_Util;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Practice_Util_Methods {


    public static Map<String ,  Object> spartanPayload () {

        Faker faker = new Faker();

        Map<String ,  Object> payload = new LinkedHashMap<>();
        payload.put("name" , faker.name().firstName());
        payload.put("gender" , faker.demographic().sex());
        payload.put("phone" , faker.number().numberBetween(3000000000L , 9999999999L));

        return payload;
    }






}
