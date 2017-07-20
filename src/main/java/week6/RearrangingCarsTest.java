package week6;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.Test;

public class RearrangingCarsTest {

  /*
   * In real world the rearrangeCars and pathForEmpty would be diff tests in case String
   * needs to change
   */
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
    int[] source = {1, 2, 0, 3};
    int[] target = {3, 1, 2, 0};
    assertEquals(res, RearrangingCars.rearrangeCars(source, target));
    assertEquals(Arrays.asList(2, 1, 0, 3), RearrangingCars.pathForEmpty(source, target));
  }

  @Test
  public void testEmptyCorrect() {
    String res =
        "move from 2 to 0\n"
            + "move from 0 to 3\n"
            + "move from 3 to 1\n"
            + "move from 1 to 2\n";
    int[] source = {1, 2, 0, 3};
    int[] target = {3, 1, 0, 2};
    assertEquals(res, RearrangingCars.rearrangeCars(source, target));
    assertEquals(Arrays.asList(2, 0, 3, 1, 2), RearrangingCars.pathForEmpty(source, target));
  }

  @Test
  public void testReverse() {
    String res =
        "move from 0 to 10\n"
            + "move from 10 to 1\n"
            + "move from 1 to 9\n"
            + "move from 9 to 10\n"
            + "move from 10 to 2\n"
            + "move from 2 to 8\n"
            + "move from 8 to 10\n"
            + "move from 10 to 3\n"
            + "move from 3 to 7\n"
            + "move from 7 to 10\n"
            + "move from 10 to 4\n"
            + "move from 4 to 6\n"
            + "move from 6 to 10\n";
    int[] target = IntStream.rangeClosed(0, 10).map(i -> 10 - i).toArray();
    int[] source = IntStream.rangeClosed(0, 10).toArray();
    assertEquals(res, RearrangingCars.rearrangeCars(source, target));
    assertEquals(Arrays.asList(0, 10, 1, 9, 10, 2, 8, 10, 3, 7, 10, 4, 6, 10),
        RearrangingCars.pathForEmpty(source, target));
  }

  @Test
  public void testReverseWithEmptyInMiddle() {
    String res =
        "move from 5 to 4\n"
            + "move from 4 to 6\n"
            + "move from 6 to 3\n"
            + "move from 3 to 7\n"
            + "move from 7 to 2\n"
            + "move from 2 to 8\n"
            + "move from 8 to 1\n"
            + "move from 1 to 9\n"
            + "move from 9 to 0\n"
            + "move from 0 to 10\n";
    int[] source = {1, 2, 3, 4, 5, 0, 6, 7, 8, 9, 10};
    int[] target = IntStream.rangeClosed(0, 10).map(i -> 10 - i).toArray();
    assertEquals(res, RearrangingCars.rearrangeCars(source, target));
    assertEquals(Arrays.asList(5, 4, 6, 3, 7, 2, 8, 1, 9, 0, 10),
        RearrangingCars.pathForEmpty(source, target));
  }

  @Test
  public void testZeroCorrectlyPlacedBefore() {
    String res =
        "move from 2 to 1\n"
            + "move from 1 to 0\n"
            + "move from 0 to 3\n"
            + "move from 3 to 1\n";
    int[] source = {1, 2, 0, 3};
    int[] target = {3, 0, 2, 1};
    assertEquals(res, RearrangingCars.rearrangeCars(source, target));
    assertEquals(Arrays.asList(2, 1, 0, 3, 1), RearrangingCars.pathForEmpty(source, target));
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
    int[] source = {1, 2, 0, 3, 5, 6, 7, 8};
    int[] target = {3, 0, 2, 1, 8, 7, 6, 5};
    assertEquals(res, RearrangingCars.rearrangeCars(source, target));
    assertThat(RearrangingCars.pathForEmpty(source, target),
        is(Arrays.asList(2, 1, 0, 3, 1, 4, 7, 1, 5, 6, 1)));
    assertEquals(Arrays.asList(2, 1, 0, 3, 1, 4, 7, 1, 5, 6, 1),
        RearrangingCars.pathForEmpty(source, target));
  }

  @Test
  public void testAlreadyCorrect() {
    int[] arr = {1, 2, 0, 3, 5, 6, 7, 8};
    assertEquals("", RearrangingCars.rearrangeCars(arr, arr));
    assertEquals(Arrays.asList(2), RearrangingCars.pathForEmpty(arr, arr));
  }
}
