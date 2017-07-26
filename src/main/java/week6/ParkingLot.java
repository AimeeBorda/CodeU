package week6;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class ParkingLot {

  private static final int EMPTY = 0;
  private static final Logger logger = Logger.getLogger(ParkingLot.class.getName());

  /*
   * First, we create a map of wrongly positioned cars, car no. -> position
   * Iteratively, replace 0 with the correct car. We remove cars that are in correct pos from map
   *
   * If 0 becomes in the correct position but map is not empty, move 0 to first incorrect car pos
   * and start iterating again until map is empty.
   *
   * Amortized Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * where n is the size of the array source
   *
   * Assumptions:
   *    1) len(source) = len(target)
   *    2) arrays contain O...n, this means no duplications
   *
   * @param source : the initial configuration of the cars
   * @param target : the target configuration of the cars
   * @return : List of Moves to be performed to transform source -> target
   */
  public static List<Move> rearrangeCars(final int[] source, final int[] target) {

    List<Move> moves = new ArrayList<Move>() {
      @Override
      public String toString() {
        return super.toString().replaceAll("\\[|]|(, )", "");
      }
    };

    if (source == null || target == null || source.length != target.length) {
      return moves;
    }

    //creates map (wrongly positioned) car id -> position and get index of the empty Box
    HashMap<Integer, Integer> misplacedCars = new HashMap<>();
    int emptyBox = populateMap(source, target, misplacedCars);

    while (!misplacedCars.isEmpty()) {
      //if emptyBox is in right pos, we swap the emptyBox with one of the wrong cars
      emptyBox = correctEmpty(misplacedCars, target, emptyBox, moves);
      emptyBox = moveEmpty(misplacedCars, target, emptyBox, moves);
    }

    logger.info(moves.toString());
    return moves;
  }


  /*
   * Updates the map misPlacedCars : cars that needs to be moved -> current position.   *
   * Returns the index of the empty box
   *
   * Time Complexity: O(n) where n is the size of array source
   * Space Complexity: O(1)
   *
   * @param source : the initial configuration of the cars
   * @param target : the target configuration of the cars
   * @param misplacedCars : the map to be updated cars to be moved -> their current position
   * @return : the index of the empty box
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
  * Amortized Time Complexity: O(1)
  * Space Complexity: O(1)
  *
  * @param misplacedCars : map to be updated with the cars to be moved
  * @param target : the target configuration of the cars
  * @param emptyBox : the index of the emptyBox
  * @param moves : the list of moves which is appended if emptyBox needs to be swapped
  * @return : the index of the emptyBox
  */
  private static int correctEmpty(Map<Integer, Integer> misplacedCars, int[] target, int emptyBox,
      List<Move> moves) {
    if (target[emptyBox] == EMPTY) {
      Entry<Integer, Integer> wrongPosCar = misplacedCars.entrySet().iterator().next();
      moves.add(new Move(emptyBox, wrongPosCar.getValue()));
      return misplacedCars.replace(wrongPosCar.getKey(), emptyBox);
    }

    return emptyBox;
  }

  /*
  * Swaps the emptyBox with the car that should be there according to target and remove
  * car from map (as it is in correct pos)
  *
  * Amortized Time Complexity: O(1)
  * Space Complexity: O(1)
  *
  * @param misplacedCars : map to be updated with the cars to be moved
  * @param target : the target configuration of the cars
  * @param emptyBox : the index of the emptyBox
  * @param moves : the list of moves which is appended if emptyBox needs to be swapped
  * @return : the index of the emptyBox
  */
  private static int moveEmpty(Map<Integer, Integer> misplacedCars, int[] target, int emptyBox,
      List<Move> moves) {
    if (misplacedCars.containsKey(target[emptyBox])) {
      moves.add(new Move(emptyBox, misplacedCars.get(target[emptyBox])));
      return misplacedCars.remove(target[emptyBox]);
    }

    return emptyBox;
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
