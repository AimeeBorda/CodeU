package week1.questionOne;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PermutationTest {

  private Permutation p;

  @Before
  public void setUp() {
    p = new Permutation();
  }

  @Test
  public void testNull() {
    assertFalse("null should not match", p.arePermutations(null, null));
    assertFalse("null should not match", p.arePermutations("", null));
    assertFalse("null should not match", p.arePermutations(null, ""));
  }

  @Test
  public void testEmptyString() {
    assertTrue("emptystring are permutations of each other", p.arePermutations("", ""));
  }

  @Test
  public void testCaseInsensitive() {
    assertTrue("permutations are case insensitive", p.arePermutations("abc", "CAB"));
  }

  @Test
  public void testFalse() {
    assertFalse("yellow is a permutation of wolley", p.arePermutations("hehehe", "wolleh"));
    assertFalse("yellow is a permutation of wolley", p.arePermutations("hellow", "hellll"));

  }

  @Test
  public void testDuplicates() {
    assertTrue("yellow is a permutation of wolley", p.arePermutations("yellow", "wolley"));
    assertTrue("abcddef is a permutation of dabdfec", p.arePermutations("abcddef", "dabdfec"));
    assertFalse("abcddef is not a permutation of dabdfe", p.arePermutations("abcddef", "dabdfe"));
    assertFalse("abcddef is not a permutation of aabdfe", p.arePermutations("abcddef", "aabdfe"));
  }

  @Test
  public void testSymbols() {
    assertTrue("\"hello world!\" is a permutation of \"hwo ellr!lod\"",
        p.arePermutations("hello world!", "hwo ellr!lod"));
    assertTrue("\"helloworld!\\b\\n\" is a permutation of \"hwo\\bellr!lod\\n\"",
        p.arePermutations("hello\nworld!\b", "hwo\bellr!lod\n"));
  }
}
