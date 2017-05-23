package week2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class BTreeTest {

  @Test
  public void testPrintAncestorEmpty() {
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();

    BTree<Integer> tree =
        builder.root(
            builder.node(
                builder.leaf(2),
                7,
                builder.leaf(9)),
            5,
            builder.leaf(3)).build();


    assertEquals("prints empty string for head", "", tree.printAncestors(5));
    assertEquals("prints \"3,5\" for ancestor on RHS", "7,5", tree.printAncestors(2));
    assertEquals("prints \"7,5\" for ancestor on LHS", "7,5", tree.printAncestors(9));
    assertEquals("prints empty string for non-existing key", "", tree.printAncestors(6));
  }

  @Test
  public void testToString() {
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();
    BTree<Integer> tree = builder.build();
    assertEquals("print empty string for empty tree", "", tree.toString());

    tree =
        builder.root(
            builder.node(
                builder.leaf(2),
                7,
                builder.leaf(9)),
            5,
            builder.leaf(3)).build();

    assertEquals("prints \"2, 7, 9, 5, 3\"", "2, 7, 9, 5, 3", tree.toString());
  }


  @Test
  public void testCommonAncestor() {
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();

    BTree<Integer> tree =
        builder.root(
            builder.node(
                builder.leaf(5),
                8,
                builder.leaf(10)),
            7,
            builder.leaf(6)).build();
    assertNull("should return null for empty tree", tree.commonAncestor(1, 2));
    assertNull("should return null if one of the element does not exists (1,5)",
        tree.commonAncestor(1, 5));
    assertNull("should return null if one of the element does not exists (5,1)",
        tree.commonAncestor(5, 2));
  }

  @Test
  public void testCommonAncestorExample() {
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();

    BTree<Integer> tree =
        builder.root(
            builder.node(
                builder.leaf(5),
                8,
                builder.leaf(10)),
            7,
            builder.leaf(6)).build();
    assertEquals("traverse tree in order should \"2, 7, 9, 5, 3\" ", "2, 7, 9, 5, 3",
        tree.toString());

    assertEquals("prints \"9\" for traversing tree (5,14)", 9, (int) tree.commonAncestor(5, 14));
    assertEquals("prints \"9\" for traversing tree (14,5)", 9, (int) tree.commonAncestor(14, 5));
    assertEquals("prints \"3\" for traversing tree (5,3)", 3, (int) tree.commonAncestor(5, 3));
    assertEquals("prints \"3\" for traversing tree (3,5)", 3, (int) tree.commonAncestor(3, 5));
    assertNull("should return null for (15,5)", tree.commonAncestor(15, 6));
  }
}
