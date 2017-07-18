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
   * and start iterating again until map is empty.
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
    int emptyBox = populateMap(source, target, cars);

    StringBuilder sb = new StringBuilder();
    while (!cars.isEmpty()) {
      emptyBox = correctEmpty(cars, target, emptyBox, sb);
      emptyBox = moveEmpty(cars, target, emptyBox, sb);
    }

    return sb.toString();
  }

  /*
   * Creates a map of all cars that needs to be moved (we never move a car that is in the right
   * position). We map car id -> position
   *
   * Returns the index of the empty box
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * where n is the length of the arrays source and target
   */
  private static int populateMap(int[] source, int[] target, HashMap<Integer, Integer> cars) {
    int emptyBox = -1;
    for (int i = 0; i < source.length; i++) {
      if (source[i] == EMPTY) {
        emptyBox = i;
      } else if (source[i] != target[i]) {
        cars.put(source[i], i);
      }
    }
    return emptyBox;
  }

  /*
  * This steps is required if emptyBox is in the right position but the list is not sorted.
  *     e.g. cars = [1, 0, 2, 3], target = [3, 0, 2, 1] and emptyBox = 1
  * We swap the emptyBox with one of the cars in the map (wrongly positioned), here we perform
  *     "move from 1 to 0"
  *
  * Time Complexity: O(1)
  * Space Complexity: O(1)
  */
  private static int correctEmpty(Map<Integer, Integer> cars, int[] target, int emptyBox,
      StringBuilder sb) {
    if (target[emptyBox] == EMPTY) {
      Entry<Integer, Integer> wrongPosCar = cars.entrySet().iterator().next();
      appendMove(emptyBox, wrongPosCar.getValue(), sb);
      return cars.replace(wrongPosCar.getKey(), emptyBox);
    }

    return emptyBox;
  }

  /*
  * Swaps the emptyBox with the car that should be in that position according to target. The car
  * is now in the correct position so is removed from the map
  *
  * Time Complexity: O(1)
  * Space Complexity: O(1)
  */
  private static int moveEmpty(Map<Integer, Integer> cars, int[] target, int emptyBox,
      StringBuilder sb) {
    if (cars.containsKey(target[emptyBox])) {
      appendMove(emptyBox, cars.get(target[emptyBox]), sb);
      return cars.remove(target[emptyBox]);
    }

    return emptyBox;
  }

  private static void appendMove(int source, int target, StringBuilder sb) {
    sb.append("move from " + source + " to " + target + "\n");
  }
}
