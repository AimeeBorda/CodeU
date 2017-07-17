package week6;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RearrangingCars {

  private static final int EMPTY = 0;

  /*
   * First, we create a map of wrongly positioned cars, car no. -> position
   * Iteratively, replace 0 with the correct car. We remove cars that are in correct pos from map
   *
   * If 0 becomes in the correct position but map is not empty, move 0 to first incorrect car pos
   * and start iterations again until map is empty.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   * Assumptions:
   *    1) len(source) = len(target)
   *    2) arrays contain O...n, this means no duplications
   *
   * where n is the length of the array
   */
  public static String rearrangeCars(final int[] source, final int[] target) {

    if (source == null || target == null || source.length != target.length || source.length == 0) {
      return "";
    }

    HashMap<Integer, Integer> cars = new HashMap<>();
    int emptyBox = -1;

    //create map car -> pos and get index of emptyBox
    for (int i = 0; i < source.length; i++) {
      if (source[i] != target[i] && source[i] != EMPTY) {
        cars.put(source[i], i);
      }

      if (source[i] == EMPTY) {
        emptyBox = i;
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!cars.isEmpty()) {
      emptyBox = correctEmpty(cars, target, emptyBox, sb);
      emptyBox = moveEmpty(cars, target, emptyBox, sb);
    }

    return sb.toString();
  }

  private static int correctEmpty(Map<Integer, Integer> cars, int[] target, int emptyBox,
      StringBuilder sb) {
    if (target[emptyBox] == EMPTY && !cars.isEmpty()) {
      Entry<Integer, Integer> wrongPosCar = cars.entrySet().iterator().next();
      appendMove(emptyBox, wrongPosCar.getValue(), sb);
      return cars.replace(wrongPosCar.getKey(), emptyBox);
    }

    return emptyBox;
  }

  private static int moveEmpty(Map<Integer, Integer> cars, int[] target, int emptyBox,
      StringBuilder sb) {
    if (cars.containsKey(target[emptyBox])) {
      appendMove(emptyBox, cars.get(target[emptyBox]), sb);
      return cars.remove(target[emptyBox]);
    }

    return emptyBox;
  }

  private static void appendMove(int source, int target, StringBuilder sb) {
    sb.append("move from ");
    sb.append(source);
    sb.append(" to ");
    sb.append(target);
    sb.append("\n");
  }


  public void printRearrangingCars(int[] source, int[] target) {
    System.out.print(rearrangeCars(source, target));
  }
}
