package week4;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import week4.flood.IslandCount;
import week4.unionFind.IslandCountUF;

@RunWith(value = Parameterized.class)
public class IslandCountTest {

  IslandCountI islandCount;

  public IslandCountTest(IslandCountI islandCount) {
    this.islandCount = islandCount;
  }

  @Parameters
  public static Collection<Object[]> getParameters() {
    return Arrays.asList(new Object[][]{
        {new IslandCount()},
        {new IslandCountUF()}
    });
  }

  @Test
  public void testEmpty() {
    assertEquals(0, islandCount.countIslands(null));

    boolean[][] emptyMap = new boolean[][]{};
    assertEquals(0, islandCount.countIslands(emptyMap));
  }

  @Test
  public void testExample() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}
    };
    assertEquals(3, islandCount.countIslands(map));
  }

  @Test
  public void testExample2() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true},
        {true, true, false, false},
        {false, true, true, false},
        {true, true, true, false}
    };
    assertEquals(2, islandCount.countIslands(map));
  }

  @Test
  public void testIslandWithHole() {
    boolean[][] map = new boolean[][]{
        {false, true, true, true},
        {false, true, false, true},
        {false, true, true, true},
        {false, false, false, false}
    };
    assertEquals(1, islandCount.countIslands(map));
  }

  @Test
  public void testOtherExample() {
    boolean[][] map = new boolean[][]{
        {true, true, false, false, false},
        {true, true, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, true}
    };
    assertEquals(3, islandCount.countIslands(map));
  }

  @Test
  public void testFShape() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true, false},
        {false, true, true, true, true},
        {true, true, false, false, false},
        {false, true, true, false, false}
    };
    assertEquals(1, islandCount.countIslands(map));
  }

  @Test
  public void testCorners() {
    boolean[][] map = new boolean[][]{
        {true, true, false, true, true},
        {true, false, false, false, true},
        {false, false, false, false, false},
        {true, false, false, false, true},
        {true, true, false, true, true}
    };
    assertEquals(4, islandCount.countIslands(map));
  }

  @Test
  public void testOneCorner() {
    boolean[][] map = new boolean[][]{
        {false, true, false},
        {true, true, true},
        {false, false, true},
        {false, true, true},
        {false, true, true}
    };
    assertEquals(1, islandCount.countIslands(map));
  }

  @Test
  public void testUIslands() {
    boolean[][] map = new boolean[][]{
        {false, false, false, false},
        {false, true, true, true},
        {false, true, false, true},
        {false, false, false, false}
    };
    assertEquals(1, islandCount.countIslands(map));
  }

  @Test
  public void testCrossIslands() {
    boolean[][] map = new boolean[][]{
        {false, true, false, false},
        {true, true, true, true},
        {false, true, false, false},
        {false, false, false, false}
    };
    assertEquals(1, islandCount.countIslands(map));
  }

  @Test
  public void testSingleIsland() {
    boolean[][] map = new boolean[][]{
        {true, true, true},
        {true, true, true}
    };
    assertEquals(1, islandCount.countIslands(map));
  }

  @Test
  public void testSingleElement() {
    boolean[][] map = new boolean[][]{
        {true}
    };
    assertEquals(1, islandCount.countIslands(map));

    map = new boolean[][]{
        {false}
    };
    assertEquals(0, islandCount.countIslands(map));
  }

  @Test
  public void testSingleRowOrCol() {
    boolean[][] map = new boolean[][]{
        {false, true, false, true, false}
    };
    assertEquals(2, islandCount.countIslands(map));

    map = new boolean[][]{
        {false},
        {false},
        {true},
        {false},
        {true}
    };
    assertEquals(2, islandCount.countIslands(map));
  }
}
