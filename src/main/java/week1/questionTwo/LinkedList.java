package week1.questionTwo;

class LinkedList<T> {

  private class Node<U> {

    private final U value;
    private Node<U> next;

    Node(U value) {
      this.value = value;
    }
  }


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
    Node temp = head;
    while (k > 0 && temp != null) {
      temp = temp.next;
      k--;
    }

    if (k < 0 || temp == null) {
      return null;
    } else {

      Node<T> res = head;
      while (temp.next != null) {
        temp = temp.next;
        res = res.next;
      }

      return res.value;
    }

  }


}
