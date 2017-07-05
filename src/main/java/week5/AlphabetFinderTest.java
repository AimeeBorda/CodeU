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
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "ARC", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testMergeCorrection() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CTT", "CTA", "CRA"}),
        is(Arrays.asList('T', 'A', 'R', 'C')));
  }


  @Test
  public void testAmbiguous() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAA", "DT", "DC"}),
        is(Arrays.asList('T', 'A', 'R', 'C', 'D')));
  }

  @Test
  public void testCasing() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "cAT", "CAR", "CAa"}),
        is(Arrays.asList('A', 'R', 'c', 'C', 'T', 'a')));
  }

  @Test
  public void testOrderPriority() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testDuplication() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "RAD", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C', 'D')));
  }

  @Test
  public void testAllLetter() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR", "CAD"}),
        is(Arrays.asList('A', 'T', 'R', 'C', 'D')));
  }

  @Test
  public void testDiffLen() {
    //in this example 'D' is before 'S' because 'D' is encountered on the third
    //character of the word whereas 'S' on the fourth
    assertThat(
        AlphabetFinder.findAlphabet(new String[]{"ARTS", "RATTA", "CAT", "CAR", "CADDDDDDDDDD"}),
        is(Arrays.asList('A', 'T', 'R', 'C', 'D', 'S')));
  }
}
