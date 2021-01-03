package Z_Practice.Review_Day06;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util_RandomObject {

    public static Map<String, Object> returnRandomObject () {

        Faker faker = new Faker();

        Map<String, Object> randomObject = new LinkedHashMap<>();
        randomObject.put("name" , faker.name().firstName());
        randomObject.put("gender" , faker.demographic().sex());
        randomObject.put("phone" , faker.number().numberBetween(3000000000L , 9999999999L));

        return randomObject;
    }

    public static void main(String[] args) {
        System.out.println(returnRandomObject());
    }

}
