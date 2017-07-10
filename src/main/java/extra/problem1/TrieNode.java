package extra.problem1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrieNode {

  private Map<Character, TrieNode> map = new HashMap<>();

  private List<String> words = new ArrayList<>();

  public void insertWord(String word) {
    if (word != null && word.length() > 0) {
      char[] chars = word.toUpperCase().toCharArray();
      Arrays.sort(chars);
      insertWord(word.toUpperCase(), chars, 0);
    }
  }

  private void insertWord(String word, char[] sortedWord, int index) {

    if (index < sortedWord.length) {
      map.putIfAbsent(sortedWord[index], new TrieNode());
      TrieNode node = map.get(sortedWord[index]);

      node.insertWord(word, sortedWord, index + 1);
    } else {
      words.add(word);
    }

  }

  public Optional<String> findWord(String word) {

    if (word == null || word.length() == 0) {
      return Optional.empty();
    }

    char[] chars = word.toUpperCase().toCharArray();
    Arrays.sort(chars);

    return findWord(chars, 0);
  }

  private Optional<String> findWord(char[] chars, int index) {
    if (index == chars.length) {
      return words.stream().findFirst();//Optional.ofNullable(words.get(0));
    } else if (map.containsKey(chars[index])) {
      return map.get(chars[index]).findWord(chars, index + 1);
    } else {
      return Optional.empty();
    }
  }
}
