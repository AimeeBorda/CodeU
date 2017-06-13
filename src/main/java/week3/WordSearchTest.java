package week3;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

public class WordSearchTest {

  private final char[][] emptyGrid = new char[0][0];
  private final char[][] grid = new char[][]{{'A', 'A', 'R'}, {'T', 'C', 'D'}};
  private final Dictionary emptyDictionary = new Dictionary(new String[]{});
  private final Dictionary dictionary = new Dictionary(new String[]{"CAR", "CARD", "CART", "CAT"});
  private WordSearch wordSearch;

  @Before
  public void setUp() {
    wordSearch = new WordSearch();
  }

  @Test
  public void testEmptyDictionary() {
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, emptyDictionary));
    assertEquals(new HashSet<String>(), wordSearch.findWords(null, emptyDictionary));
    assertEquals(new HashSet<String>(), wordSearch.findWords(grid, null));
  }

  @Test
  public void testEmptyGrid() {
    assertEquals(new HashSet<String>(), wordSearch.findWords(emptyGrid, dictionary));
  }

  @Test
  public void testExample() {
    assertEquals(new HashSet<String>() {{
      add("CAR");
      add("CARD");
      add("CAT");
    }}, wordSearch.findWords(grid, dictionary));
  }

  @Test
  public void testCaseSensitivity() {
    Dictionary d = new Dictionary(new String[]{"cAR", "CARd", "CArT", "cat"});

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

    grid[0][1] = 'A';

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
  public void testInitializeSet() {
    Dictionary d = new Dictionary(new String[]{"TATA"});
    char[][] grid = new char[][]{
        {'A', 'E', 'R'},
        {'T', 'C', 'T'}
    };

    assertEquals(2, wordSearch.initializeStack(d, grid).size());
  }

  @Test
  public void testAdjCellsEmpty() {
    assertNull(wordSearch.getUnvisitedAdjCells(null, null));
    assertNull(wordSearch.getUnvisitedAdjCells(null, emptyGrid));
  }
}