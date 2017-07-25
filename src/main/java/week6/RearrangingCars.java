package week6;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class RearrangingCars {

  private static final int EMPTY = 0;
  private static final Logger logger = Logger.getLogger(RearrangingCars.class.getName());

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
  public static List<Move> rearrangeCars(final int[] source, final int[] target) {

    List<Move> res = new ArrayList<Move>() {
      @Override
      public String toString() {
        return super.toString().replaceAll("\\[|\\]|(, )", "");
      }
    };

    if (source == null || target == null || source.length != target.length) {
      return res;
    }

    //creates map (wrongly positioned) car id -> position and get index of the empty Box
    HashMap<Integer, Integer> misplacedCars = new HashMap<>();
    int emptyBox = populateMap(source, target, misplacedCars);

    while (!misplacedCars.isEmpty()) {
      //if emptyBox is in right pos, we swap the emptyBox with one of the wrong cars
      emptyBox = correctEmpty(misplacedCars, target, emptyBox, res);
      emptyBox = moveEmpty(misplacedCars, target, emptyBox, res);
    }

    return res;
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
  private static int populateMap(int[] source, int[] target,
      HashMap<Integer, Integer> misplacedCars) {
    int emptyBox = -1;
    for (int i = 0; i < source.length; i++) {
      if (source[i] == EMPTY) {
        emptyBox = i;
      } else if (source[i] != target[i]) {
        misplacedCars.put(source[i], i);
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
      List<Move> moves) {
    if (target[emptyBox] == EMPTY) {
      Entry<Integer, Integer> wrongPosCar = cars.entrySet().iterator().next();
      move(emptyBox, wrongPosCar.getValue(), moves);
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
      List<Move> moves) {
    if (cars.containsKey(target[emptyBox])) {
      move(emptyBox, cars.get(target[emptyBox]), moves);
      return cars.remove(target[emptyBox]);
    }

    return emptyBox;
  }

  private static boolean move(int source, int target, List<Move> moves) {
    Move m = new Move(source, target);
    logger.info(m.toString());

    return moves.add(m);
  }

  private static class Move {

    private final int source;
    private final int target;

    private Move(int source, int target) {
      this.source = source;
      this.target = target;
    }

    @Override
    public String toString() {
      return "move from " + source + " to " + target + "\n";
    }
  }
}
