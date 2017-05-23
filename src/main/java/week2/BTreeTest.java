package week2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BTreeTest {

  TreeBuilder<Integer> builder;

  @Before
  public void setUp() {
    builder = TreeBuilder.getBuilder();
  }

  @Test
  public void testPrintAncestorEmpty() {
    BTree<Integer> tree = getEmptyTree();
    assertEquals("prints empty string for empty tree", "", tree.printAncestors(5));
  }

  @Test
  public void testPrintAncestorSimple() {
    BTree<Integer> tree = getSimpleTree(); //5, 8, 10, 7, 6
    assertEquals("prints empty string for root", "", tree.printAncestors(7));
    assertEquals("prints \"8, 7\" for ancestor on LHS (5)", "8, 7", tree.printAncestors(5));
    assertEquals("prints \"7\" for ancestor on RHS (6)", "7", tree.printAncestors(6));
    assertEquals("prints empty string for non-existing key", "", tree.printAncestors(9));
  }

  @Test
  public void testPrintAncestorExample() {
    BTree<Integer> tree = getExampleTree();
    assertEquals("prints \"3, 9, 16\"", "3, 9, 16", tree.printAncestors(5));
  }


  @Test
  public void testToStringEmpty() {
    BTree<Integer> tree = getEmptyTree();
    assertEquals("print empty string for empty tree", "", tree.toString());
  }

  @Test
  public void testToStringSimple() {
    BTree<Integer> tree = getSimpleTree();
    assertEquals("prints \"5, 8, 10, 7, 6\"", "5, 8, 10, 7, 6", tree.toString());
  }

  @Test
  public void testToStringExample() {
    BTree<Integer> tree = getExampleTree();
    assertEquals("prints \"1, 3, 5, 9, 14, 16, 18, 19\"", "1, 3, 5, 9, 14, 16, 18, 19",
        tree.toString());
  }

  @Test
  public void testCommonAncestorEmpty() {
    BTree<Integer> tree = getEmptyTree();
    assertNull("should return null for empty tree", tree.commonAncestor(1, 2));
  }

  @Test
  public void testCommonAncestorNonExistingElements() {

    BTree<Integer> tree = getSimpleTree();

    assertNull("should return null if one of the element does not exists (1,5)",
        tree.commonAncestor(1, 5));
    assertNull("should return null if one of the element does not exists (5,1)",
        tree.commonAncestor(5, 2));
  }

  @Test
  @Ignore
  public void testCommonAncestorSimple() {

    BTree<Integer> tree = getSimpleTree();

//    assertEquals("should return 8 for (10,5)",8, (int)tree.commonAncestor(10, 5));
//    assertEquals("should return 8 for (5,10)",8,(int) tree.commonAncestor(5, 10));
    assertEquals("should return 7 for (6,10)", 7, (int) tree.commonAncestor(6, 10));
  }

  @Test
  public void testCommonAncestorExample() {
    BTree<Integer> tree = getExampleTree();

    assertEquals("prints \"9\" for traversing tree (5,14)", 9, (int) tree.commonAncestor(5, 14));
    assertEquals("prints \"9\" for traversing tree (14,5)", 9, (int) tree.commonAncestor(14, 5));
    assertEquals("prints \"3\" for traversing tree (5,3)", 3, (int) tree.commonAncestor(5, 3));
    assertEquals("prints \"3\" for traversing tree (3,5)", 3, (int) tree.commonAncestor(3, 5));
    assertEquals("prints \"9\" for traversing tree (1,14)", 9, (int) tree.commonAncestor(1, 14));
    assertEquals("prints \"16\" for traversing tree (1,19)", 16, (int) tree.commonAncestor(1, 19));

    assertNull("should return null for (15,5)", tree.commonAncestor(15, 6));
  }

  private BTree<Integer> getExampleTree() {
    return builder.root(
        builder.node(
            builder.node(
                builder.leaf(1),
                3,
                builder.leaf(5)),
            9,
            builder.leaf(14)),
        16,
        builder.node(
            null,
            18,
            builder.leaf(19))
    ).build();
  }

  private BTree<Integer> getEmptyTree() {
    return builder.build();
  }

  private BTree<Integer> getSimpleTree() {
    return builder.root(
        builder.node(
            builder.leaf(5),
            8,
            builder.leaf(10)),
        7,
        builder.leaf(6)).build();
  }
}
