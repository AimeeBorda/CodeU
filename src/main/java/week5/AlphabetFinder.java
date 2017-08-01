package week5;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

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

    Map<Character, Node> nodes = new HashMap<>();
    Queue<List<String>> queue = new LinkedList<>();

    queue.add(Arrays.asList(words)); //O(1) as queue is empty

    while (!queue.isEmpty()) { // O(len) where len is the len of words
      List<String> group = queue.poll(); //O(1)
      group.removeIf(String::isEmpty); // O(n) where n is the number of words

      queue.addAll(
          group
              .stream()
              .collect(groupingBy(
                  s -> s.charAt(0),
                  mapping(s -> s.substring(1), toList()))).values());

      updateGraph(group.stream().map(s -> s.charAt(0)).collect(toList()), nodes); //O(nm)

    }

    return new Graph(
        nodes.values().stream().filter(n -> n.inDegree == 0).collect(toList()),
        nodes.size()
    );
  }

  /*
   * This method updates the graph with the partialOrder passed as a parameter
   *
   * Time Complexity: O(nm)
   *  where n is the length of the partial order and
   *        m is fan out for nodes
   *  Space Complexity: O(1)
   */
  private static void updateGraph(List<Character> partialOrder, Map<Character, Node> nodes) {
    Node prev = null;
    for (Character c : partialOrder) {

      Node temp = nodes.computeIfAbsent(c, Node::new);

      //if not first character or the same character from the prev node
      if (prev != null && prev.c != temp.c) {
        prev.addEdge(temp);
      }

      prev = temp;
    }
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

    final int vertices;
    final List<Node> roots;

    public Graph(List<Node> roots, int vertices) {
      this.roots = roots;
      this.vertices = vertices;
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

      while (!roots.isEmpty()) {
        Node next = roots.remove(0); //O(1)
        res.add(next.c); //O(n)
        visited++; //O(1)
        for (Node n : next.outgoing) { //O(fan-out/ E)
          removeEdge(n);
        }
      }

      //if not all the edges have been visited then there is a cycle.
      return visited == this.vertices ? Optional.of(res) : Optional.empty();
    }

    private void removeEdge(Node node) {
      node.inDegree--; //O(1)
      if (node.inDegree == 0) {
        roots.add(node); //O(n)
      }
    }
  }
}