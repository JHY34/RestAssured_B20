package day09;

import io.restassured.path.json.JsonPath;
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

    @DisplayName("GET /departments and filter the results with JsonPath groovy")
    @Test
    public void testFilterResultWithGroovy(){
        JsonPath jp = get("/departments").jsonPath();
        List<Department> allDeps = jp.getList("items.findAll {it.manager_id > 0}" , Department.class);
        allDeps.forEach(System.out::println);
        // what if I just wanted to get List<String> to store DepartmentName
        List<String> depNames = jp.getList("items.department_name") ;
        System.out.println("depNames = " + depNames);
        // -->> items.department_name (all)
        // -->> items.findAll {it.manager_id>0 }.department_name (filtered for manager_id more than 0)
        List<String> depNamesFiltered = jp.getList("items.findAll {it.manager_id>0 }.department_name") ;
        System.out.println("depNamesFiltered = " + depNamesFiltered);
        
        
        // Get all department_ID if its more than 70
        List<Integer> allDEpIds = jp.getList("items.department_id") ;
        System.out.println("allDEpIds = " + allDEpIds);
        
        
        List<Integer> allDepIdsFiltered = jp.getList("items.department_id.findAll{it>70}") ;
        System.out.println("allDepIdsFiltered = " + allDepIdsFiltered);
        
        // EVERYTHING WE TYPED HERE IS COMING FROM GROOVY
        
        // what if we have more than one condition
        // fort example department_id > 70 and  department_id < 100
        List<Integer> deps70to100 = jp.getList("items.department_id.findAll {it > 70 && it < 100}");
        System.out.println("deps70to100 = " + deps70to100);
        // prints all depts between 70-100
        
        List<String> allDeps70to100 =
                jp.getList("items.findAll{ it.department_id >=70 && it.department_id <=100}.department.name");
        System.out.println("allDeps70to100 = " + allDeps70to100);
        // prints: allDeps70to100 = []

        List<String> allDeps70To100 =
                jp.getList("items.findAll{ it.department_id >= 70 && it.department_id <= 100 }.department_name") ;
        System.out.println("allDeps70To100 = " + allDeps70To100);
        // prints: allDeps70To100 = [Public Relations, Sales, Executive, Finance]

        // sum / min / max  collect
        // get the sum of entire department_id
        int sumOfAllDepIDs = jp.getInt("items.department_id.sum()") ;
        int sumOfAllDepIDs2 = jp.getInt("items.sum {it.department_id} ") ;
        System.out.println("sumOfAllDepIDs = " + sumOfAllDepIDs);
        System.out.println("sumOfAllDepIDs2 = " + sumOfAllDepIDs2);
        
 
    }




}
