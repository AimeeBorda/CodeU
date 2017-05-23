package week2;


public class TreeBuilder<T extends Comparable<T>> {

  private Node<T> root;

  public static <T extends Comparable<T>> TreeBuilder<T> getBuilder() {
    return new TreeBuilder<>();
  }

  public TreeBuilder<T> root(Node<T> left, T key, Node<T> right) {
    root = this.node(left, key, right);
    return this;
  }

  public TreeBuilder<T> root(T key) {
    return root(null, key, null);
  }

  public Node<T> leaf(T data) {
    return new Node<>(data);
  }

  public Node<T> node(Node<T> left, T key, Node<T> right) {
    return new Node<>(key, left, right);
  }

  public BTree<T> build() {
    return new BTree<>(root);
  }


}
