package week4;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IslandCountTest {

  @Test
  public void testEmpty() {
    boolean[][] emptyMap = new boolean[][]{};
    assertEquals(0, IslandCount.countIslands(emptyMap));
  }

  @Test
  public void testExample() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}
    };
    assertEquals(3, IslandCount.countIslands(map));
  }

  @Test
  public void testExample2() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true},
        {true, true, false, false},
        {false, true, true, false},
        {true, true, true, false}
    };
    assertEquals(2, IslandCount.countIslands(map));
  }

  @Test
  public void testOtherExample() {
    boolean[][] map = new boolean[][]{
        {true, true, false, false, false},
        {true, true, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, true}
    };
    assertEquals(3, IslandCount.countIslands(map));
  }

  @Test
  public void testUIslands() {
    boolean[][] map = new boolean[][]{
        {false, false, false, false},
        {false, true, true, true},
        {false, true, false, true},
        {false, false, false, false}
    };
    assertEquals(1, IslandCount.countIslands(map));
  }

  @Test
  public void testCrossIslands() {
    boolean[][] map = new boolean[][]{
        {false, true, false, false},
        {true, true, true, true},
        {false, true, false, false},
        {false, false, false, false}
    };
    assertEquals(1, IslandCount.countIslands(map));
  }

  @Test
  public void testSingleIsland() {
    boolean[][] map = new boolean[][]{
        {true, true, true},
        {true, true, true}
    };
    assertEquals(1, IslandCount.countIslands(map));
  }

  @Test
  public void testSingleElement() {
    boolean[][] map = new boolean[][]{
        {true}
    };
    assertEquals(1, IslandCount.countIslands(map));

    map = new boolean[][]{
        {false}
    };
    assertEquals(0, IslandCount.countIslands(map));
  }

  @Test
  public void testSingleRowOrCol() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true, false}
    };
    assertEquals(2, IslandCount.countIslands(map));

    map = new boolean[][]{
        {false},
        {false},
        {true},
        {false},
        {true}
    };
    assertEquals(2, IslandCount.countIslands(map));
  }
}
