package week3;


import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

public class DictionaryTest {


  @Test
  public void testNull() {
    Dictionary d = new Dictionary(null);

    assertFalse(d.isPrefix(""));
    assertFalse(d.isPrefix(null));
    assertFalse(d.isWord(""));
    assertFalse(d.isWord(null));
  }


  @Test
  public void testEmptyDictionary() {
    Dictionary d = new Dictionary(new String[0]);

    assertFalse(d.isPrefix(""));
    assertFalse(d.isPrefix("a"));
    assertFalse(d.isWord(""));
    assertFalse(d.isWord("ab"));
  }

  @Test
  public void testPrefixSuccessful() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});

    assertTrue(d.isPrefix("C"));
    assertTrue(d.isPrefix("CA"));
    assertTrue(d.isPrefix("CAR"));
    assertTrue(d.isPrefix("CARD"));
  }

  @Test
  public void testPrefixCasing() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});

    assertFalse(d.isPrefix("c"));
  }

  @Test
  public void testWordSuccessful() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});

    assertTrue(d.isWord("CAR"));
    assertFalse(d.isWord("CA"));
  }

  @Test
  public void testWordNotPrefix() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});

    assertFalse(d.isWord("A"));
  }
}
