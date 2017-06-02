package week2;


class Tree<T> {

  private boolean bothFound = false;


  /*
   * Question 1: Given a Binary Tree and a key, write a function that prints all the ancestors
   *              of the key in the given binary tree.
   *              Example: For the key 5 and the following tree we should print: 3, 9, 16.
   *
   *  Overview: We define a private method to recursively parse through the tree searching for the
   *           element. Once the value is found, the method returns true to signal up to call stack
   *            that element is found.
   *
   *  Time: O(N) where N is the number of elements in tree
   */
  String printAncestors(Node<T> root, T key) {
    StringBuilder sb = new StringBuilder();
    printAncestors(key, root, sb);

    return sb.toString();
  }

  private boolean printAncestors(T key, Node<T> element, StringBuilder sb) {
    if (element == null) {
      return false;
    } else if (element.getKey().equals(key)) {
      //signal back that element is found and hence the recursion path back are the ancestors
      return true;
    } else if (printAncestors(key, element.getLeft(), sb) || printAncestors(key,
        element.getRight(), sb)) {
      sb.append(sb.length() > 0 ? ", " : "");
      sb.append(element.getKey().toString());

      return true;
    } else {
      return false;
    }

  }

  /* Question 2: Design an algorithm and write code to find the lowest common ancestor of two nodes
   *             in a binary tree. Avoid storing additional nodes in a data structure
   *
   * Overview: The solution is implemented recursively.
   *
   *           Possible alternatives (omitted duals) considered in the recursive commonAncestor method:
   *           1) None of the element are in the tree (else branch in commonAncestor)
   *           2) One of the element is in the tree (bothFound is not set to true)
   *           3) key1 is an ancestor of key2 => key2 (else if branch)
   *           4) key1 is descendant on the left of some node and key2 is a descendant on thr right of the same node (if branch)
   *
   *  Time: O(N) where N is the number of elements in tree
   */
  T commonAncestor(Node<T> root, T key1, T key2) {
    bothFound = false;
    T res = commonAncestor(key1, key2, root);

    return bothFound ? res : null;
  }

  private T commonAncestor(T key1, T key2, Node<T> element) {

    if (element == null) {
      return null;
    }

    T left = commonAncestor(key1, key2, element.getLeft());
    T right = commonAncestor(key1, key2, element.getRight());

    if (left != null && right != null) {
      bothFound = true;
      return element.getKey();
    } else if (element.getKey().equals(key1) || element.getKey().equals(key2)) {
      bothFound = (left != null || right != null);
      return element.getKey();
    } else {
      return left != null ? left : right;
    }

  }

  /* toString() is an inorder traversal of tree
   *
   * Time: O(N) where N is the number of elements in tree
   */

//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    toString(root, sb);
//
//    return sb.toString();
//  }
//
//  private void toString(Node<T> element, StringBuilder sb) {
//    if (element != null) {
//      toString(element.getLeft(), sb);
//      sb.append(sb.length() > 0 ? ", " : "");
//      sb.append(element.getKey().toString());
//      toString(element.getRight(), sb);
//    }
//  }
}
