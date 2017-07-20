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
    //test example that was given
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testContradiction() {
    //does the program crash on contradiction
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "ARC", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testMergeCorrection() {
    //partial order refines alphabet - there is a letter that should be moved forward
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CTT", "CTA", "CRA"}),
        is(Arrays.asList('T', 'A', 'R', 'C')));
  }

  @Test
  public void testMergeCorrectionB() {
    //partial order refines alphabet - there is a letter that should be moved backward
    assertThat(AlphabetFinder.findAlphabet(new String[]{"AC", "DR", "D", "TAR", "TAC", "TAD"}),
        is(Arrays.asList('A', 'R', 'C', 'D', 'T')));
  }

  @Test
  public void testAmbiguous() {
    //partial order is more ambigious than alphabet
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAA", "DT", "DC"}),
        is(Arrays.asList('T', 'A', 'R', 'C', 'D')));
  }

  @Test
  public void testCasing() {
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "cAT", "CAR", "CAa"}),
        is(Arrays.asList('A', 'T', 'R', 'c', 'a', 'C')));
  }

  @Test
  public void testDuplication() {
    //duplicate words
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "RA", "RAT", "CAT", "CAR"}),
        is(Arrays.asList('A', 'T', 'R', 'C')));
  }

  @Test
  public void testAllLetter() {

    //making sure all letters are included in the alphabet
    assertThat(AlphabetFinder.findAlphabet(new String[]{"ART", "RAT", "CAT", "CAR", "CAD"}),
        is(Arrays.asList('A', 'T', 'R', 'C', 'D')));
  }

  @Test
  public void testDiffLen() {
    //in this example 'D' is before 'S' because 'D' is encountered on the third
    //character of the word whereas 'S' on the fourth
    assertThat(
        AlphabetFinder.findAlphabet(new String[]{"ARTS", "RATTA", "CAT", "CAR", "CADDDDDDDDDD"}),
        is(Arrays.asList('A', 'S', 'T', 'R', 'C', 'D')));
  }

  @Test
  public void testMultipleCorrections() {
    //in this example 'D' is before 'S' because 'D' is encountered on the third
    //character of the word whereas 'S' on the fourth
    assertThat(
        AlphabetFinder.findAlphabet(new String[]{"ARTS", "RATTA", "CAT", "CAR", "CADDDDDDDDDD"}),
        is(Arrays.asList('A', 'S', 'T', 'R', 'C', 'D')));
  }

  @Test
  public void testMultipleCorrections2() {
    //multiple letters needs to move forward due to a partial order
    // e.g. alphabet = [ A, R, D, T, C, B] and po = [C, B, R] -> alphabet to [A, C, B, R, D, T]
    assertThat(
        AlphabetFinder
            .findAlphabet(new String[]{"ART", "RB", "RD", "RT", "TC", "T", "SC", "SB", "SA"}),
        is(Arrays.asList('C', 'B', 'D', 'A', 'R', 'T', 'S')));
  }

  @Test
  public void testCounterExample() {
    assertThat(
        AlphabetFinder.findAlphabet(new String[]{"AH", "BG", "CF", "DF", "DG", "EG", "EH"}),
        is(Arrays.asList('A', 'F', 'B', 'G', 'C', 'H', 'D', 'E')));
  }

}