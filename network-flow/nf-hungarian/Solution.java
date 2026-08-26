// sortsort · Hungarian Algorithm
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-hungarian

import java.util.Arrays;

public class Main {
    // O(n^3) JV-style Hungarian method; returns assignment[row] = col.
    static int[] hungarian(int[][] cost) {
        int n = cost.length;
        long INF = Long.MAX_VALUE / 4;
        long[] u = new long[n + 1], v = new long[n + 1];
        int[] p = new int[n + 1], way = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[0] = i;
            int col = 0;
            long[] minV = new long[n + 1];
            Arrays.fill(minV, INF);
            boolean[] used = new boolean[n + 1];
            do {
                used[col] = true;
                int row = p[col];
                long delta = INF;
                int nextCol = -1;
                for (int j = 1; j <= n; j++) {
                    if (!used[j]) {
                        long reduced = cost[row - 1][j - 1] - u[row] - v[j];
                        if (reduced < minV[j]) {
                            minV[j] = reduced;
                            way[j] = col;
                        }
                        if (minV[j] < delta) {
                            delta = minV[j];
                            nextCol = j;
                        }
                    }
                }
                for (int j = 0; j <= n; j++) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minV[j] -= delta;
                    }
                }
                col = nextCol;
            } while (p[col] != 0);
            while (col != 0) {
                int prev = way[col];
                p[col] = p[prev];
                col = prev;
            }
        }
        int[] assignment = new int[n];
        for (int j = 1; j <= n; j++) assignment[p[j] - 1] = j - 1;
        return assignment;
    }

    public static void main(String[] args) {
        int[][] cost = {
            {9, 2, 7},
            {6, 4, 3},
            {5, 8, 1}
        };
        int[] assignment = hungarian(cost);
        long total = 0;
        for (int i = 0; i < cost.length; i++) {
            System.out.println("Worker " + i + " -> Job " + assignment[i]
                + " (cost " + cost[i][assignment[i]] + ")");
            total += cost[i][assignment[i]];
        }
        System.out.println("Minimum total cost: " + total);
    }
}
