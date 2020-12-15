package day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestCollectionSupport {

    @Test
    public void testList () {

        List<Integer> numList = Arrays.asList(4,6,7,8,12,24,45);

        // use hamcrest matcher to test the size of this list
        assertThat(numList , hasSize(7) );

        // assert that this list contains 12
        assertThat(numList , hasItem(12));

        // assert that this list contains both 12 and 234
        assertThat(numList , hasItems(12,24));

        //assert that everyItems in the list are more than 3
        assertThat(numList , everyItem(greaterThan(3)));
        //OR
        assertThat(numList , everyItem(is(greaterThan(3))));
        // Above both annotations test the same condition



        List<String> allNames  = Arrays.asList("Roray", "Mariana","Olivia","Gulbadan","Ayxamgul","Kareem","Virginia","Tahir Zohra") ;

        assertThat(allNames , hasSize(8));

        assertThat(allNames , hasItems("Virginia" , "Olivia" , "Kareem"));

        //check every item has letter
        assertThat(allNames , everyItem(  containsString("a") ));


        // how to do and or in hamcrest syntax
        // allOf --> and logic , all of the matchers should match or it fails
        assertThat("Murat Degirmenci", allOf(startsWith("Mu"), containsString("men"))) ;

        //  anyOf -->> or logic  as long as one matcher match it will pass
        assertThat("Ramazan Alic" , anyOf(  is("Ramazan") ,  endsWith("ic") )   ) ;


    }

}
