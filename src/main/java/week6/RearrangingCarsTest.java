package week6;


import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;
import org.junit.Test;

public class RearrangingCarsTest {

  @Test
  public void testEmpty() {
    assertEquals("", RearrangingCars.rearrangeCars(null, null));
    assertEquals("", RearrangingCars.rearrangeCars(null, new int[1]));
    assertEquals("", RearrangingCars.rearrangeCars(new int[1], null));
    assertEquals("", RearrangingCars.rearrangeCars(new int[1], new int[2]));
    assertEquals("", RearrangingCars.rearrangeCars(new int[0], new int[0]));
  }

  @Test
  public void testExample() {
    String res =
        "move from 2 to 1\n"
            + "move from 1 to 0\n"
            + "move from 0 to 3\n";
    assertEquals(res, RearrangingCars.rearrangeCars(new int[]{1, 2, 0, 3}, new int[]{3, 1, 2, 0}));
  }

  @Test
  public void testReverse() {
    String res =
        "move from 0 to 2\n"
            + "move from 3 to 0\n"
            + "move from 1 to 3\n"
            + "move from 2 to 1\n"
            + "move from 3 to 2\n";
    assertEquals(res, RearrangingCars.rearrangeCars(IntStream.rangeClosed(0, 10).toArray(),
        IntStream.rangeClosed(10, 0).toArray()));
  }

  @Test
  public void testReverseWithEmptyInMiddle() {
    String res =
        "move from 0 to 10\n"
            + "move from 10 to 1\n"
            + "move from 1 to 9\n"
            + "move from 2 to 10\n"
            + "move from 3 to 2\n";
    assertEquals(res, RearrangingCars.rearrangeCars(new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8, 9, 10},
        IntStream.rangeClosed(10, 0).toArray()));
  }

  @Test
  public void testZeroCorrectlyPlacedBefore() {
    String res =
        "move from 2 to 1\n"
            + "move from 1 to 0\n"
            + "move from 0 to 3\n"
            + "move from 3 to 1\n";
    assertEquals(res, RearrangingCars.rearrangeCars(new int[]{1, 2, 0, 3}, new int[]{3, 0, 2, 1}));
  }

  @Test
  public void testZeroCorrectlyPlacedBeforeAndReverse() {
    String res =
        "move from 2 to 1\n"
            + "move from 1 to 0\n"
            + "move from 0 to 3\n"
            + "move from 3 to 1\n"
            + "move from 1 to 4\n"
            + "move from 4 to 7\n"
            + "move from 7 to 1\n"
            + "move from 1 to 5\n"
            + "move from 5 to 6\n"
            + "move from 6 to 1\n";
    assertEquals(res, RearrangingCars
        .rearrangeCars(new int[]{1, 2, 0, 3, 5, 6, 7, 8}, new int[]{3, 0, 2, 1, 8, 7, 6, 5}));
  }

  @Test
  public void testAlreadyCorrect() {
    assertEquals("", RearrangingCars
        .rearrangeCars(new int[]{1, 2, 0, 3, 5, 6, 7, 8}, new int[]{1, 2, 0, 3, 5, 6, 7, 8}));
  }
}
