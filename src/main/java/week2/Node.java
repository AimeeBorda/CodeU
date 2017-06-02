package week2;

public class Node<U> {

  private final U key;
  private Node<U> left;
  private Node<U> right;

  public Node(Node<U> left, U key, Node<U> right) {
    this.key = key;
    this.left = left;
    this.right = right;
  }

  public Node(U key) {
    this.key = key;
  }

  public U getKey() {
    return key;
  }

  public Node<U> getLeft() {
    return left;
  }

  public Node<U> getRight() {
    return right;
  }

}
