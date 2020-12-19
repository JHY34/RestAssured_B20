package day09;

import org.junit.jupiter.api.Test;
import pojo.Department;

public class TestingOutLombokDependency {

    @Test
    public void testDepartmentPOJO () {

        Department d = new Department();
        System.out.println("d.getDepartment_id() = " + d.getDepartment_id());
        // By Lombok we will able to get above getDepartment_id() method

        Department d2 = new Department(100, "ABC", 12, 1700);
        // without adding boiler points we will able to get above constructor by LOMBOK annotation

        System.out.println("d2 = " + d2);

    }

}
