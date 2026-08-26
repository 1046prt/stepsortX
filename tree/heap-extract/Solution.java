// sortsort · Heap Extract Max
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-extract

// Max-heap extract-max: swap root with last, pop, sift down

import java.util.ArrayList;
import java.util.List;

public class Main {
    // push a.get(i) down until it dominates both children
    static void siftDown(List<Integer> a, int i, int size) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < size && a.get(left) > a.get(largest)) largest = left;
            if (right < size && a.get(right) > a.get(largest)) largest = right;
            if (largest == i) return;
            int tmp = a.get(i);
            a.set(i, a.get(largest));
            a.set(largest, tmp);
            i = largest;
        }
    }

    static void buildHeap(List<Integer> a) {
        for (int i = a.size() / 2 - 1; i >= 0; i--) siftDown(a, i, a.size());
    }

    // swap root with last, shrink, then sift the new root down
    static int extractMax(List<Integer> a) {
        int top = a.get(0);
        a.set(0, a.get(a.size() - 1));
        a.remove(a.size() - 1);
        if (!a.isEmpty()) siftDown(a, 0, a.size());
        return top;
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(List.of(9, 4, 7, 1, 8, 20, 15, 3));
        buildHeap(data);
        System.out.println("heap after build: " + data);
        StringBuilder out = new StringBuilder("extracted in descending order:");
        while (!data.isEmpty()) {
            out.append(" ").append(extractMax(data));
        }
        System.out.println(out);
    }
}
