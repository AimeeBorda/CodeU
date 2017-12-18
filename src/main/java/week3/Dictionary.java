package week3;


import java.util.Arrays;

public class Dictionary {

  private final String[] words;

  Dictionary(String[] words) {
    this.words = words != null ? words : new String[0];
  }

  /*
  * Time Complexity : O(N) where N is the number of words in your dictionary
  */
  public boolean isWord(String word) {
    if (word == null) {
      return false;
    }

    return Arrays.stream(words).anyMatch(w -> w.equals(word));
  }

  /*
   * Time Complexity : O(N) where N is the number of words in your dictionary
   */
  public boolean isPrefix(String prefix) {
    if (prefix == null) {
      return false;
    }

    return Arrays.stream(words).anyMatch(w -> w.indexOf(prefix) == 0);
  }
}