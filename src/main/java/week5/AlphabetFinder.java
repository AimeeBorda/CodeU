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
   * The graph is represented using adjacency List as 1) Alphabet might be infinitely large, 2)
   * from example the graph seem to be sparse 3) we do not check the presence of arbitrary edges
   * but systematically parse through the graph.
   *
   * Main Steps: build graph from list of words -> topological sort
   *
   * Returns : empty <- if there is a cycle in the words
   *           List<Character> <- the ordered alphabet
   *
   * Time Complexity: O(build) + O(sort)
   *
   *
   */
  public static Optional<List<Character>> findAlphabet(final String[] words) {

    if (words == null) {
      return Optional.empty();
    }

    Graph graph = buildGraph(words);
    return topologicalSort(graph);
  }

  /*
   * Topological Sort: Iteratively, vertices with no incoming edges are
   * 1. added to the alphabet,
   * 2. we remove their outgoing transitions and
   * 3. add vertices with no incoming transitions to the queue
   *
   * Time Complexity:  O(|V| + |E|) in this case
   * V is the alphabet and
   * E is the length of words in the array words[]
   */
  private static Optional<List<Character>> topologicalSort(final Graph graph) {
    List<Character> res = new ArrayList<>();
    int visited = 0;
    List<Node> roots = new ArrayList<>(graph.roots);

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
    return visited == graph.vertices ? Optional.of(res) : Optional.empty();
  }

  /*
   * The while loop iteratively groups the word according to the first character.
   * The list of first characters are inserted into the graph and each group of suffixes
   * is added to the queue to infer its partial orders.
   *
   * Time Complexity: O(nml)
   * where
   *    n is the number of words,
   *    l is the len of words,
   *    m is the fan-out of words
   */
  private static Graph buildGraph(String[] words) {

    Map<Character, Node> nodes = new HashMap<>();
    Queue<List<String>> queue = new LinkedList<>();

    queue.add(Arrays.asList(words)); //O(1) as queue is empty

    while (!queue.isEmpty()) { // O(l) where l is the len of words
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

      if (!nodes.containsKey(c)) {
        nodes.put(c, new Node(c));
      }
      Node temp = nodes.get(c);

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
  }
}