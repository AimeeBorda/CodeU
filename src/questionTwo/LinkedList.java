package questionTwo;

public class LinkedList<T> {

    private class Node<T>{
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node<T> next){
            this.next = next;
        }

        public T getValue(){
            return this.value;
        }
    }


    private Node<T> head;

    /*
     * Space: O(1)
     * Time: O(N)
     */
    public void addElement(T value){
        if(head == null){
            head = new Node(value);
        }else{
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.setNext(new Node<>(value));
        }
    }

    /*
     * Space: O(1)
     * Time: O(N) where N is the number of elements in the list
     */
    public T getKthLastElement(int k){
        Node temp = head;
        while(k > 0 && temp != null){
            temp = temp.next;
            k--;
        }

        if(k < 0 || temp == null){
            return null;
        }else {

            Node<T> res = head;
            while (temp.next != null) {
                temp = temp.next;
                res= res.next;
            }

            return res.getValue();
        }

    }


}
