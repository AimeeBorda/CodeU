package week5;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

public class AlphabetFinderTest {

  @Test
  public void testEmpty() {
    assertThat(AlphabetFinder.findAlphabet(null), is(Collections.EMPTY_LIST));
    assertThat(AlphabetFinder.findAlphabet(new String[]{}), is(Collections.EMPTY_LIST));
  }

  @Test
  public void testExample() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testContradiction() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testAmbiguous() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testCasing() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testOrderPriority() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testDuplication() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }
}
