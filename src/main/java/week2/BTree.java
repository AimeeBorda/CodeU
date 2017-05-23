package week2;


class BTree<T extends Comparable<T>> {

  private Node<T> root;

  public BTree(Node<T> root) {
    this.root = root;
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
    printAncestors(key, root, sb);

    return sb.toString();
  }

  private boolean printAncestors(T key, Node<T> element, StringBuilder sb) {
    if (element != null) {
      int comparision = element.getKey().compareTo(key);

      if (comparision == 0) {
        //signal back that element is found and hence the recursion path back are the ancestors
        return true;
      } else if (printAncestors(key, element.getLeft(), sb) || printAncestors(key,
          element.getRight(), sb)) {
        sb.append(sb.length() > 0 ? ", " : "");
        sb.append(element.getKey().toString());

        return true;
      }
    }

    return false;
  }

  /* Question 2: Design an algorithm and write code to find the lowest common ancestor of two nodes
                 in a binary tree. Avoid storing additional nodes in a data structure

     Overview: The solution is implemented recursively.
               If both keys are on the LHS (less than element.getKey()), then a lower ancestor must exists.
               Once they are not on the same side
               (this includes: found one of the keys, one is on the LHS and the other on the RHS of element)
               then the current element is the lowest common ancestor provided that both keys are in the tree.
               Thus the last step is to check if keys are in the tree through findElement.

      Time: O(log N) where N is the number of elements in tree
   */
  public T commonAncestor(T key1, T key2) {
    return commonAncestor(key1, key2, root);
  }

  private T commonAncestor(T key1, T key2, Node<T> element) {

    if (element != null) {
      int comparision1 = element.getKey().compareTo(key1);
      int comparision2 = element.getKey().compareTo(key2);

      if (comparision1 == comparision2) {
        return commonAncestor(key1, key2,
            comparision1 > 0 ? element.getLeft() : element.getRight());
      } else if (findElement(key1, element) && findElement(key2, element)) {
        return element.getKey();
      }
    }
    return null;
  }

  /* Returns true if key is a descendant of element (an element is a descendant of itself)

     Time: O(log N) where N is the number of elements in tree
   */
  private boolean findElement(T key, Node<T> element) {
    if (element != null) {
      int comparision = element.getKey().compareTo(key);

      return comparision == 0 || findElement(key,
          comparision < 0 ? element.getRight() : element.getLeft());
    }

    return false;
  }

  /* toString() is an inorder traversal of tree
     Time: O(N) where N is the number of elements in tree
   */

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    toString(root, sb);

    return sb.toString();
  }

  private void toString(Node<T> element, StringBuilder sb) {
    if (element != null) {
      toString(element.getLeft(), sb);
      sb.append(sb.length() > 0 ? ", " : "");
      sb.append(element.getKey().toString());
      toString(element.getRight(), sb);
    }
  }
}
