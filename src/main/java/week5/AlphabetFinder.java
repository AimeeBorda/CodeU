package week5;


import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlphabetFinder {

  /*
   * We first get all the sets of ordering from the words (in ordering)
   * e.g. [ART, RAT, CAR, CAT] -> [[A,R,C],[R,T]]  (corrected to include single letters)
   *
   * we then call getAlphabet to infer alphabet from grouping
   *
   * Time Complexity: O(
   * Space Complexity: O(
   */
  public static List<Character> findAlphabet(String[] words) {

    if (words == null || words.length == 0) {
      return new ArrayList<>();
    }

    //get list of all ordering from words
    List<List<Character>> ordering = new ArrayList<>();
    Queue<List<String>> queue = new LinkedList<>();

    //start with all
    queue.add(Arrays.asList(words));

    while (!queue.isEmpty()) {
      List<String> suffixes = queue.poll();

      ordering.add(getOrdering(suffixes));
      queue.addAll(getSetsOfSuffixes(suffixes));

      queue.removeIf(q -> q.isEmpty());
    }

    return getAlphabet(ordering);
  }

  private static List<Character> getOrdering(List<String> suffixes) {
    return suffixes
        .stream()
        .filter(s -> s.length() > 0)
        .map(s -> s.charAt(0))
        .distinct()
        .collect(toList());
  }

  private static Collection<List<String>> getSetsOfSuffixes(List<String> suffixes) {
    Collection<List<String>> values = suffixes
        .stream()
        .filter(s -> s.length() > 1)
        .collect(Collectors.groupingBy(s -> s.charAt(0)))
        .values();

    values.forEach(v -> v.replaceAll(s -> s.substring(1)));

    return values;
  }

  private static List<Character> getAlphabet(List<List<Character>> ordering) {
    if (ordering == null || ordering.isEmpty()) {
      return new ArrayList<>();
    }

    List<Character> alphabet = new LinkedList<>(ordering.remove(0));

    ordering
        .stream()
        .distinct()
        .forEach(dep -> {
          for (int i = 0; i < dep.size(); i++) {

            int next = getNextIndex(alphabet, dep, i + 1);
            int prev = getPreviousIndex(alphabet, dep, i - 1);

            if (next == -1 && prev == -1 && !alphabet.contains(dep.get(i))) {
              alphabet.add(dep.get(i));
            } else if (next > prev) {
              alphabet.remove(dep.get(i));
              alphabet.add(Math.max(prev + 1, next), dep.get(i));
            }
          }
        });

    return alphabet;
  }

  private static int getPreviousIndex(List<Character> alphabet,
      List<Character> ordering, int index) {
    if (index >= 0) {
      return IntStream.range(index, 0)
          .filter(i -> alphabet.contains(ordering.get(i)))
          .findFirst().orElse(-1);
    }

    return -1;

  }

  private static int getNextIndex(List<Character> alphabet,
      List<Character> ordering, int index) {
    return IntStream.range(index, ordering.size())
        .filter(i -> alphabet.contains(ordering.get(i)))
        .findFirst().orElse(-1);
  }
}
