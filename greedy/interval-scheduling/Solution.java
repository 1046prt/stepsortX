// Stepsort · Interval Scheduling
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interval-scheduling

import java.util.*;

public class Main {

    static List<int[]> intervalScheduling(int[][] intervals) {
        // earliest finish time first maximizes non-overlapping intervals
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        List<int[]> chosen = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (int[] iv : intervals) {
            if (chosen.isEmpty() || iv[0] >= lastEnd) {
                chosen.add(iv);
                lastEnd = iv[1];
            }
        }
        return chosen;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 4}, {3, 5}, {0, 7}, {5, 8}, {6, 9}};
        List<int[]> chosen = intervalScheduling(intervals);
        System.out.println("Maximum intervals: " + chosen.size());
        for (int[] iv : chosen) {
            System.out.println("[" + iv[0] + ", " + iv[1] + "]");
        }
    }
}
