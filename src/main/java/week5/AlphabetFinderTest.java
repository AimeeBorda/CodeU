package week5;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

public class AlphabetFinderTest {

  @Test
  public void testEmpty() {
    assertEquals(Optional.empty(), AlphabetFinder.findAlphabet(null));
    assertThat(AlphabetFinder.findAlphabet(new String[]{}),
        is(Optional.of(Collections.emptyList())));
  }

  @Test
  public void testExample() {
    //test example that was given
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Optional.of(Arrays.asList('A', 'T', 'R', 'C'))));
  }

  @Test
  public void testContradiction() {
    //does the program crash on contradiction
    assertEquals(Optional.empty(),
        AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "ARC", "CAT", "CAR"}));
  }

  @Test
  public void testMergeCorrection() {
    //partial order refines alphabet - there is a letter that should be moved forward
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ART", "RAT", "CTT", "CTA", "CRA"}).get();
    assertThat(res, hasOrdering('A', 'R', 'C'));
    assertThat(res, hasOrdering('T', 'R'));
    assertThat(res, hasOrdering('T', 'A'));
  }

  @Test
  public void testMergeCorrectionB() {
    //partial order refines alphabet - there is a letter that should be moved backward
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"AC", "DR", "D", "TAR", "TAC", "TAD"}).get();
    assertThat(res, hasOrdering('A', 'D', 'T'));
    assertThat(res, hasOrdering('R', 'C', 'D'));
  }

  @Test
  public void testRedundantPartialOrders() {
    //partial hasOrdering in the end [T->C] is redundant.
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ART", "RAT", "CAT", "CAA", "DT", "DC"}).get();
    assertThat(res, hasOrdering('A', 'R', 'C', 'D'));
    assertThat(res, hasOrdering('T', 'C'));
    assertThat(res, hasOrdering('T', 'A'));
  }

  @Test
  public void testCasing() {
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ART", "RAT", "cAT", "CAR", "CAa"}).get();
    assertThat(res, hasOrdering('A', 'R', 'c', 'C'));
    assertThat(res, hasOrdering('R', 'a'));
    assertTrue(res.contains('T'));
  }

  @Test
  public void testDuplication() {
    //duplicate words
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ART", "RAT", "RA", "RAT", "CAT", "CAR"}).get();
    assertThat(res, hasOrdering('A', 'R', 'C'));
    assertThat(res, hasOrdering('T', 'R'));
  }

  @Test
  public void testAllLetter() {
    //making sure all letters are included in the alphabet
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR", "CAD"}).get();
    assertThat(res, hasOrdering('A', 'R', 'C'));
    assertThat(res, hasOrdering('T', 'R', 'D'));
  }

  @Test
  public void testMultipleCorrections() {
    //in this example 'D' is before 'S' because 'D' is encountered on the third
    //character of the word whereas 'S' on the fourth

    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ARTS", "RATTA", "CAT", "CAR", "CADDDDDDDDDD"}).get();

    assertThat(res, hasOrdering('A', 'R', 'C'));
    assertThat(res, hasOrdering('T', 'R', 'D'));
    assertTrue(res.contains('S'));
  }

  @Test
  public void testMultipleCorrections2() {
    //multiple letters needs to move forward due to a partial order
    // e.g. alphabet = [ A, R, D, T, C, B] and po = [C, B, R] -> alphabet to [A, C, B, R, D, T]
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"ART", "RB", "RD", "RT", "TC", "T", "SC", "SB", "SA"}).get();
    assertThat(res, hasOrdering('A', 'R', 'T', 'S'));
    assertThat(res, hasOrdering('B', 'D', 'T'));
    assertThat(res, hasOrdering('C', 'B', 'A'));
  }

  @Test
  public void testRegression() {
    List<Character> res = AlphabetFinder
        .findAlphabet(new String[]{"AH", "BG", "CF", "DF", "DG", "EG", "EH"}).get();
    assertThat(res, hasOrdering('A', 'B', 'C', 'D', 'E'));
    assertThat(res, hasOrdering('F', 'G'));
    assertThat(res, hasOrdering('G', 'H'));
  }

  private Matcher hasOrdering(final char... arr) {
    return new BaseMatcher<Character>() {
      @Override
      public boolean matches(final Object item) {
        final List<Character> res = (List<Character>) item;

        for (int i = 1; i < arr.length; i++) {
          if (res.indexOf(arr[i - 1]) > res.indexOf(arr[i])) {
            return false;
          }
        }

        return true;
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("order constraint not satisfied").appendValue(Arrays.toString(arr));
      }
    };
  }
}