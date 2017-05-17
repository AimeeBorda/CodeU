package questionTwo;

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
        LinkedList list = new LinkedList<Integer>();
        list.addElement(4); //assert that an exception is not thrown
    }

    @Test
    public void testNegative(){
        LinkedList list = new LinkedList<Integer>();
        list.addElement(4);
        assertNull("should return null for list = [4] and k = -1",list.getKthLastElement(-1));
    }

    @Test
    public void testLargerThanSize(){
        LinkedList list = new LinkedList<Integer>();
        list.addElement(4);
        assertNull("should return null for list = [4] and k = 2",list.getKthLastElement(2));
    }

    @Test
    public void testLastElement(){
        LinkedList list = new LinkedList<Integer>();
        list.addElement(4);
        list.addElement(5);
        list.addElement(6);
        assertEquals("should return 6 for list = [4,5,6] and k = 0",6,list.getKthLastElement(0));
    }


    @Test
    public void testValidValues(){
        LinkedList list = new LinkedList<Integer>();
        for(int i  = 5; i <= 15; i++) {
            list.addElement(i);
        }

        assertEquals("should return 15 for list = [5..15] and k = 0",15,list.getKthLastElement(0));
        assertEquals("should return 5 for list = [5..15] and k = 10",5,list.getKthLastElement(10));
        assertEquals("should return 10 for list = [5..15] and k = 5",10,list.getKthLastElement(5));
        assertEquals("should return 12 for list = [5..15] and k = 3",12,list.getKthLastElement(3));
        assertNull("should return null for list = [5..15] and k = 16",list.getKthLastElement(16));
    }
}
