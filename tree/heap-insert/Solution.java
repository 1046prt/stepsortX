// Stepsort · Heap Insert
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-insert

// Max-heap insert on an array with sift-up

import java.util.ArrayList;
import java.util.List;

public class Main {
    // move the value at i up while it is larger than its parent
    static void siftUp(List<Integer> a, int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (a.get(i) <= a.get(parent)) break;
            int tmp = a.get(i);
            a.set(i, a.get(parent));
            a.set(parent, tmp);
            i = parent;
        }
    }

    // append at the end, then sift up to restore heap order
    static void insert(List<Integer> a, int value) {
        a.add(value);
        siftUp(a, a.size() - 1);
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        int[] values = {15, 12, 20, 8, 25, 18, 30, 5};
        for (int v : values) {
            insert(a, v);
            System.out.println("inserted " + v + " -> array: " + a);
        }
        System.out.println("max element sits at index 0: " + a.get(0));
    }
}
