package week2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class BTreeTest {

  private BTree<Integer> exampleTree;
  private BTree<Integer> emptyTree;
  private BTree<Integer> simpleTree;


  @Before
  public void setUp() {

    exampleTree = getExampleTree();
    emptyTree = getEmptyTree();
    simpleTree = getSimpleTree();
  }

  @Test
  public void testPrintAncestorEmpty() {
    assertEquals("prints empty string for empty tree", "", emptyTree.printAncestors(5));
  }

  @Test
  public void testPrintAncestorSimple() {
    assertEquals("prints empty string for root", "", simpleTree.printAncestors(7));
    assertEquals("prints \"8, 7\" for ancestor on LHS (5)", "8, 7", simpleTree.printAncestors(5));
    assertEquals("prints \"7\" for ancestor on RHS (6)", "7", simpleTree.printAncestors(6));
    assertEquals("prints empty string for non-existing key", "", simpleTree.printAncestors(9));
  }

  @Test
  public void testPrintAncestorExample() {
    assertEquals("prints \"3, 9, 16\"", "3, 9, 16", exampleTree.printAncestors(5));
  }


  @Test
  public void testToStringEmpty() {
    assertEquals("print empty string for empty Tree", "", emptyTree.toString());
  }

  @Test
  public void testToStringSimple() {
    assertEquals("prints \"5, 8, 10, 7, 6\"", "5, 8, 10, 7, 6", simpleTree.toString());
  }

  @Test
  public void testToStringExample() {

    assertEquals("prints \"1, 3, 5, 9, 14, 16, 18, 19\"", "1, 3, 5, 9, 14, 16, 18, 19",
        exampleTree.toString());
  }

  @Test
  public void testCommonAncestorEmpty() {
    assertNull("should return null for empty tree", emptyTree.commonAncestor(1, 2));
  }

  @Test
  public void testCommonAncestorNonExistingElements() {
    assertNull("should return null if one of the element does not exists (1,5)",
        simpleTree.commonAncestor(1, 5));
//        assertNull("should return null if one of the element does not exists (5,2)",
//                simpleTree.commonAncestor(5, 2));
  }

  @Test
  public void testCommonAncestorSimple() {
    assertEquals("should return 8 for (10,5)", 8, (int) simpleTree.commonAncestor(10, 5));
    assertEquals("should return 8 for (5,10)", 8, (int) simpleTree.commonAncestor(5, 10));
    assertEquals("should return 7 for (6,10)", 7, (int) simpleTree.commonAncestor(6, 10));
  }

  @Test
  public void testCommonAncestorExample() {
    assertEquals("prints \"9\" for traversing tree (5,14)", 9,
        (int) exampleTree.commonAncestor(5, 14));
    assertEquals("prints \"9\" for traversing tree (14,5)", 9,
        (int) exampleTree.commonAncestor(14, 5));
    assertEquals("prints \"3\" for traversing tree (5,3)", 3,
        (int) exampleTree.commonAncestor(5, 3));
    assertEquals("prints \"3\" for traversing tree (3,5)", 3,
        (int) exampleTree.commonAncestor(3, 5));
    assertEquals("prints \"9\" for traversing tree (1,14)", 9,
        (int) exampleTree.commonAncestor(1, 14));
    assertEquals("prints \"16\" for traversing tree (1,19)", 16,
        (int) exampleTree.commonAncestor(1, 19));

    assertNull("should return null for (15,5)", exampleTree.commonAncestor(15, 6));
  }

  private BTree<Integer> getExampleTree() {
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();
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
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();
    return builder.build();
  }

  private BTree<Integer> getSimpleTree() {
    TreeBuilder<Integer> builder = TreeBuilder.getBuilder();

    return builder.root(
        builder.node(
            builder.leaf(5),
            8,
            builder.leaf(10)),
        7,
        builder.leaf(6)).build();
  }
}
