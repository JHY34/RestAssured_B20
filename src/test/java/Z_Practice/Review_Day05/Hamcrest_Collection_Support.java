package Z_Practice.Review_Day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest_Collection_Support {

    @Test
    public void assertionTests () {

        List<Integer> listOfNumbers = Arrays.asList(4,6,8,12,34,56,90);

        assertThat (listOfNumbers, hasSize (7));

        assertThat(listOfNumbers , hasItems(4,6,8));

        assertThat(listOfNumbers , not (hasItem(40)));

        assertThat(listOfNumbers , everyItem(is (greaterThan( 3 ))));

        assertThat(listOfNumbers , everyItem(is (not(greaterThan( 30 )))));

        assertThat(listOfNumbers , everyItem(is (lessThan( 100 ))));


    }




}
