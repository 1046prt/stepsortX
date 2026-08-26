// sortsort · Find Median from Data Stream
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/find-median-data-stream

import java.util.*;

public class Main {
    static class MedianFinder {
        private PriorityQueue<Long> small = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Long> large = new PriorityQueue<>();

        public void addNum(long num) {
            small.offer(num);
            large.offer(small.poll());
            // keep small >= large in size
            if (large.size() > small.size()) {
                small.offer(large.poll());
            }
        }

        public double findMedian() {
            if (small.size() > large.size()) return small.peek();
            return (small.peek() + large.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        for (long x : new long[]{5, 15, 1, 3}) {
            finder.addNum(x);
            System.out.println("added " + x + " -> median " + finder.findMedian());
        }
    }
}
