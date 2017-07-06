package week5;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

public class AlphabetFinder {

  /*
   * Two main steps:
   * 1) We first get all the partially ordered lists of characters from the words
   * e.g. [ART, RAT, CAR, CAT] -> [[A,R,C],[R],[A],[A],[A],[T],[T],[R,T]]
   * It will include duplicates and single letters e.g. for ART we have [[R],[T]]
   * to make sure all letters are included
   *
   * 2) We pass the ordering to getAlphabet that infers the alphabet from the list of words
   *
   * Time Complexity: O(
   * Space Complexity: O(
   */
  public static List<Character> findAlphabet(String[] words) {

    if (words == null || words.length == 0) {
      return new ArrayList<>();
    }

    return getAlphabet(getPartialOrderLists(words));
  }

  /*
   * Iteratively takes set of word lists and for each list get the ordering
   * and a set of word list that rise up after dropping the first character.
   * The alogrithm starts with a singleton set containing the complete list of words
   *
   * We remove empty list within the loop to speed up.
   *
   * Time Complexity: O(n) where n is the number of words
   */
  private static List<List<Character>> getPartialOrderLists(String[] words) {
    List<List<Character>> res = new ArrayList<>();

    //contain a queue of (sorted) word lists yet to be processed
    Queue<List<String>> queue = new LinkedList<>();

    queue.add(Arrays.asList(words));

    while (!queue.isEmpty()) {
      Map<Character, List<String>> groups =
          queue.poll()
              .stream()
              .filter(s -> s.length() > 0)
              .collect(groupingBy(
                  s -> s.charAt(0),
                  LinkedHashMap::new,
                  mapping(s -> s.substring(1), toList())));

      res.add(distinct(groups.keySet()));
      queue.addAll(distinct(groups.values()));

      queue.removeIf(List::isEmpty);
    }
    return res;
  }

  private static <T> List<T> distinct(Collection<T> words) {
    return words.stream().distinct().collect(toList());
  }


  /*
   * Given a list of partial orders, the method infers the alphabet by iteratively
   * merging partial-orders
   *
   * Time Complexity: O(n) where n is the number of partial-order
   */
  private static List<Character> getAlphabet(List<List<Character>> orders) {
    return orders
        .stream()
        .distinct()
        .reduce(AlphabetFinder::merge)
        .orElse(new ArrayList<>());
  }

  /*
  * Refines an alphabet according to a partial-order.
  * 1) We start from the last letter. This guarantee that ambiguous letters
  * are inserted at the last valid position (the letter T from the example)
  * 2) We keep consistent the largest valid index up to which the letters in dep can be inserted
  *     e.g., for alphabet [A, R, T] and dep [T, R] index should be 1 for T as it should not come after R
  *     a) if letter is not in alphabet we insert it in index (which will be largest valid position)
  *     b) if letter is in alphabet but after index, it mean we found a more accurate ordering and
  *        the position of the letter should be corrected.
  *
  * Time Complexity: O(n) where n is the number of partial-order
  */
  private static List<Character> merge(List<Character> alphabet, List<Character> dep) {
    int index = alphabet.size();
    ListIterator<Character> iterator = dep.listIterator(dep.size());

    while (iterator.hasPrevious()) {
      char letter = iterator.previous();
      int temp = alphabet.indexOf(letter);

      if (temp >= 0 && temp < index) {
        index = temp;
      } else {
        alphabet.remove((Character) letter);
        alphabet.add(index, letter);
      }
    }

    return alphabet;
  }
}