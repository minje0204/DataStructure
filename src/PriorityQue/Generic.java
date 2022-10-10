package PriorityQue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/* Generic Naming Convention
 * * E - Element (used extensively by the Java Collections Framework)
 * K - Key
 * N - Number
 * T - Type
 * V - Value
 * S,U,V etc. - 2nd, 3rd, 4th types
 * */

// Comparator<? super T> 이해해보기
class Car implements Comparable<Car> {

    private int carNum;

    @Override
    public int compareTo(Car o) {
        return 0;
    }
}

class eCar extends Car {

    private int e;
}

public class Generic {

    public static void main(String[] args) throws Exception {
        List<eCar> list = new ArrayList<>();

        list.add(new eCar());
        list.add(new eCar());

        Collections.sort(list);

    }
}