package extra.problem1;


import java.util.Optional;

public class CarLicense {

  public static Optional<String>[] findShortestAnagram(String[] licences, String[] dictionary) {
    if (dictionary == null || dictionary.length == 0 || licences.length == 0) {
      return new Optional[]{};
    }

    Optional<String>[] shortestWords = new Optional[licences.length];
    TrieNode root = buildDictionary(dictionary);

    for (int i = 0; i < licences.length; i++) {
      String license = licences[i].replaceAll("[^a-zA-Z]", "");
      shortestWords[i] = root.findWord(license);
    }

    return shortestWords;
  }

  private static TrieNode buildDictionary(String[] dictionary) {
    TrieNode node = new TrieNode();
    for (String word : dictionary) {
      node.insertWord(word);
    }

    return node;
  }
}
