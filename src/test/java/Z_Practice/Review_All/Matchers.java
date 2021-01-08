package Z_Practice.Review_All;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.core.IsSame;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Matchers {
    @Order(-2)
    @Test
    public void ZATestA () {
        assertThat (4, not(3));
        assertThat (201, is(equalTo(201)));
        assertThat ("java", not("jav"));
    }

    @Order(-2)
    @Test
    public void AAATestAA () {
        assertThat (4, not(3));
        assertThat (201, is(equalTo(201)));
        assertThat ("java", not("jav"));
    }
}
