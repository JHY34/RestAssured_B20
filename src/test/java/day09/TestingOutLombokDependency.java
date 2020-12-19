package day09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Department;
import testbase.HR_ORDS_TestBase;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestingOutLombokDependency extends HR_ORDS_TestBase {

    @Test
    public void testDepartmentPOJO () {

        Department d = new Department();
        System.out.println("d.getDepartment_id() = " + d.getDepartment_id());
        // By Lombok we will able to get above getDepartment_id() method

        Department d2 = new Department(100, "ABC", 12, 1700);
        // without adding boiler points we will able to get above constructor by LOMBOK annotation

        System.out.println("d2 = " + d2);

    }

    @DisplayName("GET /departments and save List of POJO")
    @Test
    public void testDepartmentJsonArrayToListOfPOJO () {

        List<Department> allDeps = get("/departments").jsonPath().getList("items", Department.class);
        allDeps.forEach(System.out::println);
        // prints everything

        // COPY THE CONTENT OF THIS LIST INTO NEW LIST
        // AND ONLY PRINT IF THE DEP MANAGER_ID IS NOT NULL

        // allDeps.removeIf(p->p.getManager_id()!=0);
        // THIS WILL GIVE ERROR BEUCASE IT IS UNMODIFIBLE

        List<Department> allDepsCopy = new ArrayList<>(allDeps);
        allDepsCopy.removeIf(p-> p.getManager_id() == 0);
        allDepsCopy.forEach(System.out::println);

    }

}
