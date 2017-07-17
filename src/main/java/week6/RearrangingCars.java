package week6;


import java.util.Arrays;

public class RearrangingCars {

  /*
   *
   * Time Complexity:
   * Space Complexity:
   * Assumptions:
   *    1) len(source) = len(target)
   *    2) arrays contain O...n, where n is the length of the array (this means no duplications)
   */
  public static String rearrangeCars(int[] source, int[] target) {

    if (source == null || target == null || source.length != target.length || source.length == 0) {
      return "";
    }

    return "";
  }

  public String getMoves(Move[] moves) {
    return Arrays.stream(moves).map(Move::toString).reduce(String::concat).orElse("");
  }

  private class Move {

    int source;
    int target;

    @Override
    public String toString() {
      return String.format("move from %d to %d \n", source, target);
    }
  }
}
