package week3;


import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

public class WordSearchTest {

  WordSearch wordSearch;

  @Before
  public void setUp() {
    wordSearch = new WordSearch();
  }

  @Test
  public void testEmptyDictionary() {
    Dictionary d = new Dictionary(new String[]{});
    char[][] grid = new char[][]{
        {'A', 'A', 'R'},
        {'T', 'C', 'D'}
    };
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, d));
  }

  @Test
  public void testEmptyGrid() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});
    char[][] grid = new char[0][0];

    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, d));
  }

  @Test
  public void testExample() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});
    char[][] grid = new char[][]{
        {'A', 'A', 'R'},
        {'T', 'C', 'D'}
    };
    assertEquals(new HashSet<String>() {{
      add("CAR");
      add("CARD");
      add("CAT");
    }}, wordSearch.findWords(grid, d));
  }

  @Test
  public void testCaseSensitivity() {
    Dictionary d = new Dictionary(new String[]{"cAR", "CARd", "CArT", "cat"});
    char[][] grid = new char[][]{
        {'A', 'A', 'R'},
        {'T', 'C', 'D'}
    };
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, d));
  }

  @Test
  public void testForAdjacentRule() {
    Dictionary d = new Dictionary(new String[]{"RAT"});
    char[][] grid = new char[][]{
        {'A', 'E', 'R'},
        {'T', 'C', 'D'}
    };
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, d));

    grid = new char[][]{
        {'A', 'A', 'R'},
        {'T', 'C', 'D'}
    };

    assertEquals(new HashSet<String>() {{
      add("RAT");
    }}, wordSearch.findWords(grid, d));
  }

  @Test
  public void testForDuplication() {
    Dictionary d = new Dictionary(new String[]{"TATA"});
    char[][] grid = new char[][]{
        {'A', 'E', 'R'},
        {'T', 'C', 'T'}
    };
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, d));

  }


}
