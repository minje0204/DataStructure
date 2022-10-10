package PriorityQue;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQue<E> {

    transient Object[] queue;  //직렬화시 제외
    int size;
    private static final int INITIAL_CAPACITY = 11;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8; // object header size 8
    private final Comparator<? super E> comparator;  // E가 eCar일때 ?는 Car가 올 수 있다. 그리고 Car는 하다.

    public PriorityQue() {
        this(null, INITIAL_CAPACITY);  //상속자 오버로딩
    }

    public PriorityQue(Comparator<? super E> comp, int size) {
        this.comparator = comp;
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        this.queue = new Object[size];
    }

    public boolean add(E val) {
        return offer(val);
    }

    public boolean offer(E val) {
        if (val == null) {
            throw new NullPointerException();
        }
        int i = size;
        if (i >= queue.length) {
            grow(i + 1);
        }
        siftUp(i, val);
        size = i + 1;
        return true;
    }

    public E poll() {
        final Object[] es;
        final E result;

        if ((result = (E) (es = queue)[0]) != null) {
            final int n;
            final E x = (E) es[(n = size--)];
            es[n] = null;
            if (n > 0) {
                siftDown(0, (E) queue[size - 1]);//루트 빠진자리에 제일 밑에꺼 채우고 siftDown하기
            }
        }
        return result;
    }

    private void siftDown(int k, E v) {
        if (comparator != null) {
            siftDownUsingComparator(k, v, queue, size, comparator);
        } else {
            siftDownComparable(k, v, queue, size);
        }
    }

    private static <T> void siftDownUsingComparator(int k, T v, Object[] es, int n,
        Comparator<? super T> cmp) {

        int half = n >>> 1;
        while (k < half) {
            int child = (k >> 1) + 1; // assume left child is least
            Object c = es[child];
            int right = child + 1;
            if (right < n && cmp.compare((T) c, (T) es[right]) > 0) {
                c = es[child = right];
            }
            if (cmp.compare(v, (T) c) <= 0) {
                break;
            }
            es[k] = c;
            k = child;
        }
        es[k] = v;
    }

    private static <T> void siftDownComparable(int k, T v, Object[] es, int n) {
        Comparable<? super T> key = (Comparable<? super T>) v;

        int half = n >>> 1; //leaf node 빼고 계산
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = es[child];
            int right = child + 1;
            if (right < n && ((Comparable<? super T>) c).compareTo((T) es[right]) > 0) {
                c = es[child = right];
            }
            if (key.compareTo((T) c) <= 0) {
                break;
            }
            es[k] = c;
            k = child;
        }
        es[k] = v;
    }

    private void heapify() {
        final Object[] es = queue;
        int n = size, i = (n >>> 1) - 1; //leaf node까지 해줄 필요없다.
        final Comparator<? super E> cmp;
        if ((cmp = comparator) == null) {
            for (; i >= 0; i--) {
                siftDownComparable(i, (E) es[i], es, n);
            }
        } else {
            for (; i >= 0; i--) {
                siftDownUsingComparator(i, (E) es[i], es, n, cmp);
            }
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + (oldCapacity < 64 ? oldCapacity + 2 : oldCapacity >> 1);

        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
        }
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private void siftUp(int k, E x) {
        if (comparator != null) {
            siftUpUsingComparator(k, x, queue, comparator);
        } else {
            siftUpComparable(k, x, queue);
        }
    }

    private static <T> void siftUpComparable(int k, T x,
        Object[] queue) { // Since this is a static method - it cannot grab generic information from anywhere else.
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1)
                >>> 1;   // 왜 >>가 아니라 >>>를 썼을까? https://stackoverflow.com/questions/19058859/what-does-mean-in-java
            Object e = queue[parent];
            if (key.compareTo((T) e) >= 0) {
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    private static <E> void siftUpUsingComparator(int k, E x, Object[] queue,
        Comparator<? super E> cmp) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;  // k-1 인 이유 -> root가 idx 0이니깐
            Object e = queue[parent];
            if (cmp.compare(x, (E) e) >= 0) {
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }
}
