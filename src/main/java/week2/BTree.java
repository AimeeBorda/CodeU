package week2;


class BTree<T extends Comparable<T>> {

  private class Node<U extends Comparable<U>> {

    private final U key;
    private Node<U> left;
    private Node<U> right;

    Node(U key) {
      this.key = key;
    }
  }


  private Node<T> head;

  /* Implemented recursively
      Time: O(log N) where N is the number of elements in tree
   */
  public void insert(T key) {
    head = insert(key, head);
  }

  private Node<T> insert(T key, Node<T> element) {
    if (element == null) {
      return new Node<>(key);
    } else if (element.key.compareTo(key) < 0) {
      element.right = insert(key, element.right);
    } else if (element.key.compareTo(key) > 0) {
      element.left = insert(key, element.left);
    }
    return element;
  }

  /* Question 1: Given a Binary Tree and a key, write a function that prints all the ancestors
                 of the key in the given binary tree.
                 Example: For the key 5 and the following tree we should print: 3, 9, 16.

     Overview: We define a private method to recursively parse through the tree searching for the
              element. The method prints any node that are within the search path.

     Time: O(log N) where N is the number of elements in tree

   */
  public String printAncestors(T key) {
    StringBuilder sb = new StringBuilder();
    printAncestors(key, head, sb);

    return sb.toString();
  }

  private void printAncestors(T key, Node<T> element, StringBuilder sb) {
    if (element != null) {
      int comparision = element.key.compareTo(key);

      if (comparision < 0) {
        sb.insert(0, sb.length() > 0 ? "," : "");
        sb.insert(0, element.key.toString());
        printAncestors(key, element.right, sb);
      } else if (comparision > 0) {
        sb.insert(0, sb.length() > 0 ? "," : "");
        sb.insert(0, element.key.toString());
        printAncestors(key, element.left, sb);
      }
    } else {
      sb.delete(0, sb.length());
    }
  }

  /* Question 2: Design an algorithm and write code to find the lowest common ancestor of two nodes
                 in a binary tree. Avoid storing additional nodes in a data structure

     Overview: The solution is implemented recursively.
               If both keys are on the LHS (less than element.key), then a lower ancestor must exists.
               Once they are not on the same side
               (this includes: found one of the keys, one is on the LHS and the other on the RHS of element)
               then the current element is the lowest common ancestor provided that both keys are in the tree.
               Thus the last step is to check if keys are in the tree through findElement.

      Time: O(log N) where N is the number of elements in tree
   */
  public T commonAncestor(T key1, T key2) {
    return commonAncestor(key1, key2, head);
  }

  private T commonAncestor(T key1, T key2, Node<T> element) {

    if (element != null) {
      int comparision1 = element.key.compareTo(key1);
      int comparision2 = element.key.compareTo(key2);

      if (comparision1 == comparision2) {
        return commonAncestor(key1, key2, comparision1 > 0 ? element.left : element.right);
      } else if (findElement(key1, element) && findElement(key2, element)) {
        return element.key;
      }
    }
    return null;
  }

  /* Returns true if key is a descendant of element (an element is a descendant of itself)

     Time: O(log N) where N is the number of elements in tree
   */
  private boolean findElement(T key, Node<T> element) {
    if (element != null) {
      int comparision = element.key.compareTo(key);

      return comparision == 0 || findElement(key, comparision < 0 ? element.right : element.left);
    }

    return false;
  }
}
