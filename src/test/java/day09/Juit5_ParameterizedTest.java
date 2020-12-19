package day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Juit5_ParameterizedTest {

    @ParameterizedTest  // when you say @ParameterizedTest you need to add source as seen below
    @ValueSource (ints = {5,6,7,8,9,10})   // each number is injected every test -> 5 test 1, 6 test2 ....
    public void test1 (int myNumber) {

        System.out.println("myNumber = " + myNumber);
        // assert 5,6,7,8,9,10  all less than 10
        assertTrue(myNumber <=100) ;
        // all PASSED



    }

}
