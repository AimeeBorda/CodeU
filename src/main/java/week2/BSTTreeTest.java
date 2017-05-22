package week2;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BSTTreeTest {

    @Test
    public void testPrintAncestorEmpty(){
        BSTTree<Integer> tree = new BSTTree<Integer>();
        assertEquals("prints empty string for null tree", "",tree.printAncestors(5));
        tree.insert(5);
        tree.insert(7);
        tree.insert(3);
        tree.insert(2);
        tree.insert(9);
        assertEquals("prints empty string for head","", tree.printAncestors(5));
        assertEquals("prints \"3,5\" for ancestor on RHS","3,5", tree.printAncestors(2));
        assertEquals("prints \"7,5\" for ancestor on LHS","7,5", tree.printAncestors(9));
        assertEquals("prints empty string for non-existing key","", tree.printAncestors(6));
    }


    @Test
    public void testInsert(){
        BSTTree<Integer> tree = new BSTTree<Integer>();
        assertEquals("prints empty string for empty tree","", tree.traverse());
        tree.insert(5);
        tree.insert(7);
        tree.insert(3);
        tree.insert(2);
        tree.insert(9);
        assertEquals("prints \"2,3,5,7,9\" for traversing tree","2,3,5,7,9", tree.traverse());
        tree.insert(3);
        assertEquals("prints \"2,3,5,7,9\" and ignore duplicate keys","2,3,5,7,9", tree.traverse());
    }

   /* @Test
    public void testCommonAncestor(){
        BSTTree<Integer> tree = new BSTTree<Integer>();
        assertNull("should return null for empty tree",tree.commonAncestor(1,2));
        tree.insert(5);
        assertNull("should return null if one of the element does not exists (1,5)",tree.commonAncestor(1,5));
        assertNull("should return null if one of the element does not exists (5,1)",tree.commonAncestor(5,2));
    }

    @Test
    public void testInsertExample(){
        BSTTree<Integer> tree = new BSTTree<Integer>();
        tree.insert(5);
        tree.insert(7);
        tree.insert(3);
        tree.insert(2);
        tree.insert(9);
        assertEquals("prints \"9\" for traversing tree",9, (int)tree.commonAncestor(5,14));
    }*/
}
