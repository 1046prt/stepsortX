// Stepsort · Optimal Merge Pattern
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/optimal-merge

import java.util.*;

public class Main {

    static long optimalMergeCost(List<Integer> fileSizes) {
        // always combine the two smallest files first
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        for (int size : fileSizes) minHeap.add((long) size);

        long totalCost = 0;
        while (minHeap.size() > 1) {
            long first = minHeap.poll();
            long second = minHeap.poll();
            long cost = first + second;
            totalCost += cost;
            minHeap.add(cost);
        }
        return totalCost;
    }

    public static void main(String[] args) {
        List<Integer> sizes = List.of(4, 3, 2, 6);
        System.out.println("Minimum merge cost: " + optimalMergeCost(sizes));
    }
}
