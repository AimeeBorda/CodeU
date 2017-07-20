package week5;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlphabetFinder {

  public static List<Character> findAlphabet(String[] words) {

    if (words == null) {
      return new ArrayList<>();
    }

    List<Node> roots = new ArrayList();
    int edges = buildGraph(words, roots);
    return topologicalSort(roots, edges);
  }

  private static List<Character> topologicalSort(List<Node> roots, int edges) {
    List<Character> res = new ArrayList<>();
    while (!roots.isEmpty()) {
      Node next = roots.remove(0);
      res.add(next.c);
      while (!next.outgoing.isEmpty()) {
        Node dep = next.removeEdge();
        edges--;
        if (dep.incoming.size() == 0) {
          roots.add(dep);
        }
      }
    }

    //still to find cycles
    return edges == 0 ? res : new ArrayList<>(); //this will be exception
  }

  private static int buildGraph(String[] words, List<Node> roots) {

    Map<Character, Node> nodes = new HashMap<>();
    Queue<List<String>> queue = new LinkedList<>();

    queue.add(Arrays.asList(words));

    while (!queue.isEmpty()) {
      Map<Character, List<String>> groups =
          queue.poll()
              .stream()
              .filter(s -> s.length() > 0)
              .collect(groupingBy(
                  s -> s.charAt(0),
                  LinkedHashMap::new,
                  mapping(s -> s.substring(1), toList())));

      updateGraph(groups.keySet().toArray(new Character[0]), nodes);
      queue.addAll(groups.values());

    }

    roots.addAll(nodes.values().stream().filter(n -> n.incoming.isEmpty()).collect(toList()));

    return nodes.values().stream().map(n -> n.outgoing.size()).reduce(0, Integer::sum);
  }


  private static void updateGraph(Character[] partialOrder, Map<Character, Node> nodes) {
    Node prev = null;
    for (Character c : partialOrder) {

      if (!nodes.containsKey(c)) {
        nodes.put(c, new Node(c));
      }
      Node temp = nodes.get(c);

      if (prev != null) {
        prev.addEdge(temp);
      }

      prev = temp;
    }
  }

  private static class Node {

    final char c;
    List<Node> outgoing = new ArrayList<>();
    List<Node> incoming = new ArrayList<>();

    private Node(char c) {
      this.c = c;
    }

    public void addEdge(Node n) {
      n.incoming.add(this);
      outgoing.add(n);
    }

    public Node removeEdge() {
      Node node = this.outgoing.remove(0);
      node.incoming.remove(this);
      this.outgoing.remove(node);

      return node;
    }
  }
}