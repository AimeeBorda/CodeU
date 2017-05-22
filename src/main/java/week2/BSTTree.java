package week2;


class BSTTree<T extends Comparable<T>> {

    private class Node<U extends Comparable<U>>{

        private final U key;
        private Node<U> left;
        private Node<U> right;

        public Node(U key) {
            this.key = key;
        }
    }


    private Node<T> head;

    public void insert(T key){
        head = insert(key, head);
    }

    private Node<T> insert(T key, Node<T> element){
        if(element == null){
            return new Node<>(key);
        }else if(element.key.compareTo(key) < 0){
            element.right =  insert(key,element.right);
        }else if(element.key.compareTo(key) > 0){
            element.left =  insert(key,element.left);
        }
        return element;
    }

    public String traverse(){
        StringBuilder sb = new StringBuilder();
        traverse(head,sb);

        return sb.toString();
    }

    private void traverse(Node<T> element,StringBuilder sb){
        if(element != null){
            traverse(element.left,sb);
            sb.append(sb.length() > 0 ? ",":"");
            sb.append(element.key.toString());
            traverse(element.right,sb);
        }
    }

    public String printAncestors(T key){
        StringBuilder sb = new StringBuilder();
        printAncestors(key, head, sb);

        return sb.toString();
    }

    private void printAncestors(T key, Node<T> element, StringBuilder sb){
        if(element != null) {
            int comparision = element.key.compareTo(key);

            if (comparision < 0) {
                sb.insert(0,sb.length() > 0 ? ",":"");
                sb.insert(0, element.key.toString() );
                printAncestors(key, element.right, sb);
            } else if (comparision > 0) {
                sb.insert(0,sb.length() > 0 ? ",":"");
                sb.insert(0, element.key.toString() );
                printAncestors(key, element.left, sb);
            }
        }else{
            sb.delete(0,sb.length());
        }
    }

    public T commonAncestor(T key1, T key2){
        return commonAncestor(key1, key2, head);
    }

    private T commonAncestor(T key1, T key2, Node<T> element) {

        if(element != null){
            int comparision1 = element.key.compareTo(key1);
            int comparision2 = element.key.compareTo(key2);

            if(comparision1 == comparision2){
                return commonAncestor(key1, key2, comparision1 > 0 ? element.left: element.right);
            }else
                return element.key;
        }
        return null;
    }
}
