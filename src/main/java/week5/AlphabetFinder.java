package week5;


import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

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

    Map<Character, Node> nodes =
        Arrays.stream(words)
            .flatMapToInt(String::chars)
            .distinct()
            .mapToObj(s -> (char) s)
            .collect(toMap(Function.identity(), Node::new));

    Graph graph = new Graph(nodes);

    for (int i = 1; i < words.length; i++) {
      String word1 = words[i - 1];
      String word2 = words[i];

      getIndex(word1, word2).ifPresent(index -> {
        graph.addEdge(word1.charAt(index), word2.charAt(index));
      });
    }

    return graph;
  }

  private static Optional<Integer> getIndex(String word1, String word2) {
    int index = 0;
    while (index < word1.length() && index < word2.length() && word1.charAt(index) == word2
        .charAt(index)) {
      index++;
    }
    return index < word1.length() && index < word2.length() ? Optional.of(index) : Optional.empty();
  }


  private static class Node {

    final char c;
    List<Node> outgoing = new ArrayList<>();
    int inDegree = 0;

    private Node(char c) {
      this.c = c;
    }

    public void addEdge(Node n) {
      n.inDegree++;
      outgoing.add(n);
    }
  }

  private static class Graph {

    Map<Character, Node> nodes;

    public Graph(Map<Character, Node> nodes) {
      this.nodes = nodes;
    }


    /*
   * Topological Sort based on Kahn's algorithm.
   *
   * Iteratively, vertices with no incoming edges are
   * 1. added to the alphabet,
   * 2. we remove their outgoing transitions and
   * 3. add vertices with no incoming transitions to the queue
   *
   * Time Complexity:  O(|V| + |E|) in this case
   * V is the alphabet and
   * E is the length of words in the array words[]
   */
    private Optional<List<Character>> topologicalSort() {
      List<Character> res = new ArrayList<>();
      int visited = 0;
      List<Node> roots = nodes.values().stream().filter(n -> n.inDegree == 0).collect(toList());

      while (!roots.isEmpty()) {
        Node next = roots.remove(0); //O(1)
        res.add(next.c); //O(n)
        visited++; //O(1)
        for (Node n : next.outgoing) { //O(fan-out/ E)
          n.inDegree--; //O(1)
          if (n.inDegree == 0) {
            roots.add(n); //O(n)
          }
        }
      }

      //if not all the edges have been visited then there is a cycle.
      return visited == this.nodes.size() ? Optional.of(res) : Optional.empty();
    }


    public void addEdge(char from, char to) {
      nodes.get(from).addEdge(nodes.get(to));
    }
  }
}