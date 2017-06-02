package week2;


import java.util.ArrayList;
import java.util.Optional;

class Tree<T> {

  /*
   * Question 1: Given a Binary Tree and a key, write a function that prints all the ancestors
   *              of the key in the given binary tree.
   *              Example: For the key 5 and the following tree we should print: 5, 3, 9, 16.
   *
   *  Overview: We define a private method to recursively parse through the tree searching for the
   *           element. Once the value is found, the method returns true to signal up to call stack
   *            that element is found and the ancestors are all added to an arraylist which in the
   *            end is the resulting string
   *
   *  Time: O(N) where N is the number of elements in tree
   */
  String printAncestors(Node<T> root, T key) {
    ArrayList<T> ancestors = new ArrayList<>();
    findAncestors(key, root, ancestors);

    return ancestors.toString();
  }

  private boolean findAncestors(T key, Node<T> element, ArrayList<T> ancestors) {
    if (element == null) {
      return false;
    }

    if (element.getKey().equals(key)
        || findAncestors(key, element.getLeft(), ancestors)
        || findAncestors(key, element.getRight(), ancestors)) {

      // signals back that element is found and hence the recursion path back are the ancestors
      ancestors.add(element.getKey());
      return true;
    }

    return false;
  }


  /* Question 2: Design an algorithm and write code to find the lowest common ancestor of two nodes
   *             in a binary tree. Avoid storing additional nodes in a data structure
   *
   * Overview: The solution uses findAncestors method to get the ancestor of both keys and then
   *            return the first common element in the lists
   *
   */
  Optional<T> commonAncestor(Node<T> root, T key1, T key2) {

    ArrayList<T> key1Ancestor = new ArrayList<>();
    ArrayList<T> key2Ancestor = new ArrayList<>();

    if (findAncestors(key1, root, key1Ancestor) && findAncestors(key2, root, key2Ancestor)) {

      key1Ancestor.retainAll(key2Ancestor);

      return key1Ancestor.stream().findFirst();
    }

    return Optional.empty();
  }

}