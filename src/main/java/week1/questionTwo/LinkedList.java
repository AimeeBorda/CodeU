package week1.questionTwo;

class LinkedList<T> {

  private Node<T> head;

  /*
   * Space: O(1)
   * Time: O(N) where N is the number of elements in the list
   */
  public void addElement(T value) {
    if (head == null) {
      head = new Node<>(value);
    } else {
      Node temp = head;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = new Node<>(value);
    }
  }

  /*
   * Space: O(1)
   * Time: O(N) where N is the number of elements in the list
   */
  public T getKthLastElement(int k) {
    Node<T> temp = head;

    if (k < 0) {
      return null;
    }

    while (k > 0 && temp != null) {
      temp = temp.next;
      k--;
    }

    if (temp == null) {
      return null;
    }

    Node<T> res = head;
    while (temp.next != null) {
      temp = temp.next;
      res = res.next;
    }

    return res.value;
  }

  private class Node<U> {

    private final U value;
    private Node<U> next;

    Node(U value) {
      this.value = value;
    }
  }


}
