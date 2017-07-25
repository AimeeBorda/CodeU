package week6;


import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;
import org.junit.Test;

public class ParkingLotTest {


  @Test
  public void testEmpty() {
    assertEquals("", ParkingLot.rearrangeCars(null, null).toString());
    assertEquals("", ParkingLot.rearrangeCars(null, new int[1]).toString());
    assertEquals("", ParkingLot.rearrangeCars(new int[1], null).toString());
    assertEquals("", ParkingLot.rearrangeCars(new int[1], new int[2]).toString());
    assertEquals("", ParkingLot.rearrangeCars(new int[0], new int[0]).toString());
  }

  @Test
  public void testExample() {
    String res =
        "move from 2 to 1\n"
            + "move from 1 to 0\n"
            + "move from 0 to 3\n";
    int[] source = {1, 2, 0, 3};
    int[] target = {3, 1, 2, 0};
    assertEquals(res, ParkingLot.rearrangeCars(source, target).toString());
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
    assertEquals(res, ParkingLot.rearrangeCars(source, target).toString());
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
    assertEquals(res, ParkingLot.rearrangeCars(source, target).toString());
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
    assertEquals(res, ParkingLot.rearrangeCars(source, target).toString());
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
    assertEquals(res, ParkingLot.rearrangeCars(source, target).toString());
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
    assertEquals(res, ParkingLot.rearrangeCars(source, target).toString());
  }

  @Test
  public void testAlreadyCorrect() {
    int[] arr = {1, 2, 0, 3, 5, 6, 7, 8};
    assertEquals("", ParkingLot.rearrangeCars(arr, arr).toString());
  }
}
