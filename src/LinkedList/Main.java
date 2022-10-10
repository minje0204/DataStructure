package LinkedList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./src/LinkedList/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        LinkedList<String> myLlist = new LinkedList<String>();
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            if (!st.hasMoreTokens()) {
                break;
            }
            String cmd = st.nextToken();
            Object arg = null;
            if (st.hasMoreTokens()) {
                arg = st.nextToken();
            }

            switch (cmd) {
                case "add":
                    System.out.println("add " + arg);
                    System.out.println(myLlist.add((String) arg));
                    break;
                case "print":
                    System.out.println("print whole list");
                    System.out.println(myLlist.toString());
                    break;
                case "get":
                    System.out.println("getting " + arg + " index element!");
                    System.out.println(myLlist.get(Integer.parseInt((String) arg)));
                    break;
                case "remove":
                    System.out.println("removing " + arg + " index element!");
                    System.out.println(myLlist.remove(Integer.parseInt((String) arg)));
                    break;
                case "contains":
                    System.out.println("checking list has " + arg);
                    System.out.println(myLlist.contains((String) arg));
                    break;
            }
            printSplitter();
        }
    }

    public static void printSplitter() {
        System.out.println("==========================================");
    }
}


