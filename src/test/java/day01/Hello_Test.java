package day01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Day 1 Hello Test")  // This is seen on the Test Results Screen
public class Hello_Test {

    // Junit5 Annotations
    // @BeforeAll, @AfterAll, @BeforeEach, @AfterAll
    // @DisplayName @Disabled

    @BeforeAll
    public static void setUp() {
        System.out.println("@BeforeAll is running");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("@BeforeAll is running");
    }


    @BeforeEach
    public  void setUpTest() {
        System.out.println("@BeforeEach is running");
    }

    @AfterEach
    public  void tearDownTest() {
        System.out.println("@AfterEach is running");
    }



    @DisplayName("Test 1 + 3 = 4")   // This is seen on the Test Results Screen
    @Test
    public void test() {
        System.out.println("Test 1 is running");
        Assertions.assertEquals(4, 3+1);
    }

    @Disabled   // will disable this this test execution if there any errors that cause a problem -- Test is ignored
    @DisplayName("Test 4 * 3 = 12")  // This is seen on the Test Results Screen
    @Test
    public void test2() {
        // assert 4 x 3 = 12
        // by the above static import we dont need to write Assertions before the assertEquals() method
        System.out.println("Test 2 is running");
        assertEquals(4*3, 12);
    }

}
