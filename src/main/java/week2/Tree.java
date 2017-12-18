package week2;

class Tree<T> {

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
    }

    if (element.getKey().equals(key)) {
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
    Result res = commonAncestor(key1, key2, root);

    return res != null && res.isAncestor ? res.key : null;
  }

  private Result commonAncestor(T key1, T key2, Node<T> element) {

    if (element == null) {
      return null;
    }

    Result resLeft = commonAncestor(key1, key2, element.getLeft());
    Result resRight = commonAncestor(key1, key2, element.getRight());

    if (resLeft != null && resRight != null) {
      return new Result(element.getKey());
    } else if (element.getKey().equals(key1) || element.getKey().equals(key2)) {
      boolean isAncestor = (resLeft != null || resRight != null || key1 == key2);
      return new Result(element.getKey(), isAncestor);
    } else {
      return resLeft != null ? resLeft : resRight;
    }
  }


  private class Result {

    boolean isAncestor;
    T key;

    public Result(T key) {
      isAncestor = true;
      this.key = key;
    }

    public Result(T key, boolean isAncestor) {
      this.isAncestor = isAncestor;
      this.key = key;
    }
  }

}