package week3;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

public class WordSearchTest {

  private WordSearch wordSearch;

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
    assertEquals(new HashSet<String>(), wordSearch.findWords(null, d));
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, null));
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

  @Test
  public void testInitializeMapEmpty() {
    assertNull(wordSearch.initializeMap(new Dictionary(new String[0]), null));
    assertNull(wordSearch.initializeMap(null, new char[0][0]));
  }

  @Test
  public void testInitializeMap() {
    Dictionary d = new Dictionary(new String[]{"TATA"});
    char[][] grid = new char[][]{
        {'A', 'E', 'R'},
        {'T', 'C', 'T'}
    };

    assertEquals(2, wordSearch.initializeMap(d, grid).get("T").size());
    assertEquals(1, wordSearch.initializeMap(d, grid).size());

  }

  @Test
  public void testAdjCellsEmpty() {
    assertNull(wordSearch.getUnvisitedAdjCells(null));
  }

  @Test
  public void testAdjCells() {
    Dictionary d = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});
    char[][] grid = new char[][]{
        {'A', 'A', 'R'},
        {'T', 'C', 'D'}
    };

    assertEquals(1, wordSearch.initializeMap(d, grid).get("C").size());
    assertEquals(1, wordSearch.initializeMap(d, grid).size());

  }

}
