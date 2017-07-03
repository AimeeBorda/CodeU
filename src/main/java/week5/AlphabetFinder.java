package week5;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AlphabetFinder {

  public static List<Character> findAlphabet(String[] words) {

    if (words == null || words.length == 0) {
      return new LinkedList<>();
    }

    List<List<String>> suffixes = new LinkedList<>();
    List<List<Character>> ordering = new LinkedList<>();

    suffixes.add(Arrays.asList(words));

    while (!suffixes.isEmpty()) {
      LinkedList<Character> tempOrdering = new LinkedList<>();
      LinkedList<String> tempSuffix = new LinkedList<>();

      for (String s : suffixes.remove(0)) {
        if (!tempOrdering.contains(s.charAt(0))) {
          tempOrdering.add(s.charAt(0));
        }

        if (s.length() > 1) {
          tempSuffix.add(s.substring(1));
        }
      }

      tempSuffix.removeIf(s -> s.length() <= 1);
      if (tempSuffix.size() > 1) {
        suffixes.add(tempSuffix);
      }

      if (tempOrdering.size() > 1) {
        ordering.add(tempOrdering);
      }
    }

    return getAlphabet(ordering);
  }

  private static List<Character> getAlphabet(List<List<Character>> ordering) {
    List<Character> alphabet = new LinkedList<>();

    while (!ordering.isEmpty()) {

    }
    return alphabet;
  }
}
