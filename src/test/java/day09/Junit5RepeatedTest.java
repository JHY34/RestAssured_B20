package day09;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.RepeatedTest;

public class Junit5RepeatedTest {

    @RepeatedTest(10)
    public void testRepeating () {

        Faker faker = new Faker();

        System.out.println("Name is " + faker.funnyName().name());
    }


}
