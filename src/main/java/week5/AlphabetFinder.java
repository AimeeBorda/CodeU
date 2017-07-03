package week5;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AlphabetFinder {

  public static List<Character> findAlphabet(String[] words) {

    if (words == null || words.length == 0) {
      return new LinkedList<>();
    }

    List<Character> alphabet = new LinkedList<>();
    List<List<String>> suffixes = new LinkedList<>();
    suffixes.add(Arrays.asList(words));

    while (!suffixes.isEmpty()) {
      List<String> suffix = suffixes.remove(0);
      List<String> temp = new LinkedList<>();
      List<Character> firstChar = new LinkedList<>();

      for (String s : suffix) {
        if (!firstChar.contains(s.charAt(0))) {
          firstChar.add(s.charAt(0));
          if (temp.size() > 1) {
            suffixes.add(temp);
          }
          temp.clear();
        }

        if (s.length() > 1) {
          temp.add(s.substring(1));
        }
      }

      if (firstChar.size() > 1) {
        merge(alphabet, firstChar);
      }

    }
    return alphabet;
  }

  private static void merge(List<Character> alphabet, List<Character> ordering) {

  }
}
