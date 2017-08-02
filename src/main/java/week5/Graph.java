package week5;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class Graph {

  Map<Character, Node> nodes;

  public Graph(Set<Character> nodes) {
    this.nodes = nodes.stream().collect(toMap(Function.identity(), Node::new));
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
  public Optional<List<Character>> topologicalSort() {
    List<Character> res = new ArrayList<>();
    int visited = 0;
    List<Node> roots = nodes.values().stream().filter(n -> n.inDegree == 0).map(Node::clone)
        .collect(toList());

    while (!roots.isEmpty()) {
      Node next = roots.remove(0); //O(1)
      res.add(next.c); //O(n)
      visited++; //O(1)
      for (Node n : next.outgoing) { //O(fan-out/ E)
        n.inDegree--; //O(1)
        if (n.inDegree == 0) {
          roots.add(n.clone()); //O(n)
        }
      }
    }

    //if not all the edges have been visited then there is a cycle.
    return visited == this.nodes.size() ? Optional.of(res) : Optional.empty();
  }


  public void addEdge(char from, char to) {
    nodes.get(from).addEdge(nodes.get(to));
  }

  private class Node {

    final char c;
    List<Node> outgoing = new ArrayList<>();
    int inDegree = 0;

    private Node(char c) {
      this.c = c;
    }

    private Node(Node node) {
      this.c = node.c;
      this.outgoing = node.outgoing;
      this.inDegree = node.inDegree;
    }

    @Override
    public Node clone() {
      return new Node(this);
    }

    public void addEdge(Node n) {
      n.inDegree++;
      outgoing.add(n);
    }
  }
}