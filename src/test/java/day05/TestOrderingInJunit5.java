package day05;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.MethodOrderer.* ;
// this import will help us not to write MethodOrderer at the below annotation

// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// @TestMethodOrder(Random.class)  // order randomly
public class TestOrderingInJunit5 {

    // by default the tests will will run by alphebetical order
    // If I need to make any order I can use @Order
    // @Order will accept even negative numbers too
    // you need to import --> (import static org.junit.jupiter.api.MethodOrderer.* ;  )

    // Mainly There are 4 ways to order
    // 1. MethodName
    // 2. Display Name
    // 3. By @Order() annotation
    // 4. Randomly ordering
    // But there soudlnt be any conflict. You need to select ONLY one of them
    // It will follow what you type at the above of the class as seen below
    // @TestMethodOrder(Random.class)  <-- RUN randomly

    @Order(0)
    @Test
    public void testA () {
        System.out.println("Running Test A");
    }

    @Order(-14)
    @Test
    public void testC () {
        System.out.println("Running Test C");
    }


    @Order(4)
    @Test
    public void testD () {
        System.out.println("Running Test D");
    }

    @Order(2)
    @Test
    public void testB () {
        System.out.println("Running Test B");
    }

}
