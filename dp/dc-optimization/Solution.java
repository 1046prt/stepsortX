// Stepsort · Divide & Conquer Optimization
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dc-optimization

import java.util.*;

public class Main {
    static long[] prevRow, cur, prefix;
    static int layer;

    static void rec(int lo, int hi, int klo, int khi) {
        if (lo > hi) return;
        int mid = (lo + hi) / 2;
        long best = Long.MAX_VALUE;
        int arg = Math.max(klo, layer - 1);
        for (int k = Math.max(klo, layer - 1); k <= Math.min(khi, mid - 1); k++) {
            long seg = prefix[mid + 1] - prefix[k];
            long cand = prevRow[k] + seg * seg;
            if (cand < best) { best = cand; arg = k; }
        }
        cur[mid] = best;
        rec(lo, mid - 1, klo, arg);
        rec(mid + 1, hi, arg, khi);
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 3, 9, 4, 1};
        int n = arr.length, G = 3;
        prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];
        prevRow = new long[n + 1];
        Arrays.fill(prevRow, Long.MAX_VALUE);
        prevRow[0] = 0;
        cur = new long[n + 1];

        for (layer = 1; layer <= G; layer++) {
            Arrays.fill(cur, Long.MAX_VALUE);
            rec(layer, n - 1, layer - 1, n - 1);
            prevRow = cur.clone();
        }
        System.out.println("min cost: " + prevRow[n - 1]);
    }
}
