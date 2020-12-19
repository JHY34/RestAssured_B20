package pojo;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
// By this way we will able to get all getter, setter, constructors, toString by LOMBOK
public class Department {

    private int department_id ;
    private String department_name ;
    private int Manager_id ;
    private int location_id ;

}
