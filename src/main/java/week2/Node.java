package week2;

public class Node<U extends Comparable<U>> {

  private final U key;
  private Node<U> left;
  private Node<U> right;

  public Node(U key, Node<U> left, Node<U> right) {
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

  public void setLeft(Node<U> left) {
    this.left = left;
  }

  public Node<U> getRight() {
    return right;
  }

  public void setRight(Node<U> right) {
    this.right = right;
  }

}
