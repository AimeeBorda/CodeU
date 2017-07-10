package extra.problem1;

import static org.junit.Assert.assertArrayEquals;

import java.util.Optional;
import org.junit.Test;

/**
 * Created by aimeeborda on 10/07/2017.
 */
public class CarLicenceTest {

  @Test
  public void testEmpty() {

  }

  @Test
  public void testExample() {
    assertArrayEquals(new Optional[]{Optional.of("SORT"), Optional.of("CAR")}, CarLicense
        .findShortestAnagram(new String[]{"RT 123SO", "RC 10014A"},
            new String[]{"SORTS", "SORT", "CAR", "TEST", "TORS", "HELP"}));
  }
}
