package week3;


import java.util.Arrays;

public class Dictionary {

  private String[] words;

  Dictionary(String[] words) {
    this.words = words;
  }

  /*
  * Time Complexity : O(N) where N is the number of words in your dictionary
  */
  public boolean isWord(String word) {
    if (words == null || word == null) {
      return false;
    }

    return Arrays.stream(words).filter(w -> w.equals(word)).findAny().isPresent();
  }

  /*
   * Time Complexity : O(N) where N is the number of words in your dictionary
   */
  public boolean isPrefix(String prefix) {
    if (words == null || prefix == null) {
      return false;
    }

    return Arrays.stream(words).filter(w -> w.indexOf(prefix) == 0).findAny().isPresent();
  }
}
