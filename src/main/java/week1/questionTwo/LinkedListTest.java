package week1.questionTwo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class LinkedListTest {

    @Test
    public void testNull(){
        LinkedList list = new LinkedList<Integer>();
        assertNull("should return null for list = [] and k = 0",list.getKthLastElement(0));
        assertNull("should return null for list = [] and k = 1",list.getKthLastElement(1));
    }

    @Test
    public void testAddElement(){
        LinkedList<Integer> list = new LinkedList<>();
        list.addElement(4); //assert that an exception is not thrown
    }

    @Test
    public void testNegative(){
        LinkedList<Integer> list = new LinkedList<>();
        list.addElement(4);
        assertNull("should return null for list = [4] and k = -1",list.getKthLastElement(-1));
    }

    @Test
    public void testLargerThanSize(){
        LinkedList<Integer> list = new LinkedList<>();
        list.addElement(4);
        assertNull("should return null for list = [4] and k = 2",list.getKthLastElement(2));
    }

    @Test
    public void testLastElement(){
        LinkedList<Integer> list = new LinkedList<>();
        list.addElement(4);
        list.addElement(5);
        list.addElement(6);
        assertEquals("should return 6 for list = [4,5,6] and k = 0",6,(int)list.getKthLastElement(0));
    }


    @Test
    public void testValidValues(){
        LinkedList<Integer> list = new LinkedList<>();
        for(int i  = 5; i <= 15; i++) {
            list.addElement(i);
        }

        assertEquals("should return 15 for list = [5..15] and k = 0",15,(int)list.getKthLastElement(0));
        assertEquals("should return 5 for list = [5..15] and k = 10",5,(int)list.getKthLastElement(10));
        assertEquals("should return 10 for list = [5..15] and k = 5",10,(int)list.getKthLastElement(5));
        assertEquals("should return 12 for list = [5..15] and k = 3",12,(int)list.getKthLastElement(3));
        assertNull("should return null for list = [5..15] and k = 16",list.getKthLastElement(16));
    }

    @Test
    public void testDeleteEmpty(){
        LinkedList<Integer> list = new LinkedList<>();

        assertEquals("should return 0 for list = []",0,list.removeElement(3));
        for(int i  = 5; i <= 15; i++) {
            list.addElement(i);
        }
        assertEquals("should return 0 for list = [5..15]",0,list.removeElement(3));
        assertEquals("should return 1 for list = [5..15]",1,list.removeElement(5));
        assertEquals("should return 1 for list = [6..15]",1,list.removeElement(15));
        assertEquals("should return 1 for list = [6..14]",1,list.removeElement(7));
    }

    @Test
    public void testDeleteAndKthElement(){
        LinkedList<Integer> list = new LinkedList<>();
        for(int i  = 5; i <= 15; i++) {
            list.addElement(i % 5);
        }

        assertEquals("should return 2 for list = [0..4]^3 and k = 3",2,(int)list.getKthLastElement(3));
        assertEquals("should delete 2 occurrences of 3",2,list.removeElement(3));
        assertEquals("should return 1 for list = [0124]^3 and k = 3",1,(int)list.getKthLastElement(3));
    }
}
