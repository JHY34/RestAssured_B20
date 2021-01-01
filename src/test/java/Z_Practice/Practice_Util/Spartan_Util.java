package Z_Practice.Practice_Util;


import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class Spartan_Util {


    public static Map<String, Object> getOneSpartanPayload() {

        Faker faker = new Faker();

        Map<String, Object> spartanPayload = new LinkedHashMap<>();
        spartanPayload.put("name", faker.name().firstName());
        spartanPayload.put("gender", faker.demographic().sex());
        spartanPayload.put("phone", faker.number().numberBetween(3_000_000_000L, 9_999_999_999L));

        return spartanPayload;

    }

}
