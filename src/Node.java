public class Node<E> {

    private Node next;
    private E value;

    public Node(E value) {
        this.next = null;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
            "next=" + next +
            ", value=" + value +
            '}';
    }


}
