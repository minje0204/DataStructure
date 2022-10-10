package LinkedList;

public class LinkedList<E> {

    private Node<E> head;

    public E get(int n) {
        int idx = 0;
        E rt = null;
        if (!isEmpty()) {
            for (Node<E> cur = head.getNext(); cur.getNext() != null; cur = cur.getNext()) {
                if (n == idx++) {
                    rt = cur.getValue();
                }
            }
        }
        return rt;
    }

    public boolean add(E val) {
        Node<E> cur = head;
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        Node<E> newNode = new Node<>(val);
        cur.setNext(newNode);

        return true;
    }

    public boolean remove(int n) { //연결을 끊은 Node객체는 어떻게 지우지 Gc가 알아서 지우나? 확인은 어떻게 하지
        int idx = 0;  // you use multiple variables of the same type for(Object var1 = null, var2 = null; ...) 다른타입은 for loop안에서 init 불가
        for (Node<E> cur = head; cur.getNext() != null; cur = cur.getNext(), idx++) {
            if (n == idx++) {
                cur.setNext(cur.getNext().getNext());
                return true;
            }
        }
        return false;
    }

    public boolean contains(E val) {
        for (Node<E> cur = head; cur.getNext() != null; cur = cur.getNext()) {
            if (val.equals(cur.getValue())) { // == 는 false를 리턴한다. ->
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.head.getNext() == null;
    }

    public LinkedList() {
        this.head = new Node<E>(null); //E를 쓰는 제너릭인데 초기값을 어떻게 지정해줄까?
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        if (!isEmpty()) {
            for (Node<E> cur = head.getNext(); cur.getNext() != null; cur = cur.getNext(), idx++) {
                sb.append(idx).append(": ").append(cur.getValue()).append(" ");
            }
        }
        return sb.toString();
    }
}
