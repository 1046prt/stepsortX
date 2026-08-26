// sortsort · Merge Intervals
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-intervals

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (!merged.isEmpty() && merged.get(merged.size() - 1)[1] >= interval[0]) {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            } else {
                merged.add(interval);
            }
        }
        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] res = merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < res.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append("[").append(res[i][0]).append(", ").append(res[i][1]).append("]");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
