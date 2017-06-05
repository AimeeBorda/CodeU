package week3;


import java.util.Arrays;

public class Dictionary {

  private String[] words;

  Dictionary(String[] words) {
    this.words = words;
  }

  public boolean isWord(String word) {
    if (words == null || word == null) {
      return false;
    }

    return Arrays.binarySearch(words, word) >= 0;
  }

  public boolean isPrefix(String prefix) {
    if (words == null || prefix == null) {
      return false;
    }

    return Arrays.stream(words).filter(w -> w.indexOf(prefix) == 0).findAny().isPresent();
  }
}
