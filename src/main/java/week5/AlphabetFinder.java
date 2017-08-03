package week5;


import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AlphabetFinder {

  /*
   * The graph is represented using adjacency List as 1) Alphabet might be unbounded, 2)
   * from example the graph seem to be sparse 3) we do not check the presence of arbitrary edges
   * but systematically traverse through the graph.
   *
   * Main Steps:
   *  1. Build partial order graph of characters from word list.
   *  2. Topological sort to generate a total order.
   *
   * Returns : empty <- if there is a cycle in the words
   *           List<Character> <- the ordered alphabet
   *
   * Time Complexity: O(build) + O(sort)
   *                : O(n*m*len) + O(|alphabet| + len)
   *                : O(n*m*len)
   *
   * where
   *    n is the number of words,
   *    len is the len of words,
   *    m is the fan-out of words
   */
  public static Optional<List<Character>> findAlphabet(final String[] words) {

    if (words == null) {
      return Optional.empty();
    }

    return buildGraph(words).topologicalSort();
  }


  /*
   * The while loop iteratively groups the word according to the first character.
   * The list of first characters are inserted into the graph and each group of suffixes
   * is added to the queue to infer its partial orders.
   *
   * Time Complexity: O(n*m*len)
   * where
   *    n is the number of words,
   *    len is the len of words,
   *    m is the fan-out of words
   */
  private static Graph buildGraph(String[] words) {

    Set<Character> nodes =
        Arrays.stream(words)
            .flatMapToInt(String::chars)
            .mapToObj(s -> (char) s)
            .collect(toSet());

    Graph graph = new Graph(nodes);

    for (int i = 1; i < words.length; i++) {
      String word1 = words[i - 1];
      String word2 = words[i];

      getEdge(word1, word2)
          .ifPresent(index -> graph.addEdge(word1.charAt(index), word2.charAt(index)));
    }

    return graph;
  }

  private static Optional<Integer> getEdge(String word1, String word2) {
    int len = Math.min(word1.length(), word2.length());
    for (int i = 0; i < len; i++) {
      if (word1.charAt(i) != word2.charAt(i)) {
        return Optional.of(i);
      }
    }
    return Optional.empty();
  }
}