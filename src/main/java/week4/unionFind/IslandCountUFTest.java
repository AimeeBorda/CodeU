package week4.unionFind;


import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class IslandCountUFTest {

  @Test
  public void testEmpty() {
    Assert.assertEquals(0, IslandCountUF.countIslandsUF(null));

    boolean[][] emptyMap = new boolean[][]{};
    assertEquals(0, IslandCountUF.countIslandsUF(emptyMap));
  }

  @Test
  public void testExample() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}
    };
    assertEquals(3, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testExample2() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true},
        {true, true, false, false},
        {false, true, true, false},
        {true, true, true, false}
    };
    assertEquals(2, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testIslandWithHole() {
    boolean[][] map = new boolean[][]{
        {false, true, true, true},
        {false, true, false, true},
        {false, true, true, true},
        {false, false, false, false}
    };
    assertEquals(1, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testOtherExample() {
    boolean[][] map = new boolean[][]{
        {true, true, false, false, false},
        {true, true, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, true}
    };
    assertEquals(3, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testUIslands() {
    boolean[][] map = new boolean[][]{
        {false, false, false, false},
        {false, true, true, true},
        {false, true, false, true},
        {false, false, false, false}
    };
    assertEquals(1, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testCrossIslands() {
    boolean[][] map = new boolean[][]{
        {false, true, false, false},
        {true, true, true, true},
        {false, true, false, false},
        {false, false, false, false}
    };
    assertEquals(1, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testSingleIsland() {
    boolean[][] map = new boolean[][]{
        {true, true, true},
        {true, true, true}
    };
    assertEquals(1, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testSingleElement() {
    boolean[][] map = new boolean[][]{
        {true}
    };
    assertEquals(1, IslandCountUF.countIslandsUF(map));

    map = new boolean[][]{
        {false}
    };
    assertEquals(0, IslandCountUF.countIslandsUF(map));
  }

  @Test
  public void testSingleRowOrCol() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true, false}
    };
    assertEquals(2, IslandCountUF.countIslandsUF(map));

    map = new boolean[][]{
        {false},
        {false},
        {true},
        {false},
        {true}
    };
    assertEquals(2, IslandCountUF.countIslandsUF(map));
  }
}
