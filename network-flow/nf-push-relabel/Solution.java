// sortsort · Push-Relabel
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-push-relabel

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int pushRelabel(int n, int[][] capacity, int s, int t) {
        int[][] residual = new int[n][];
        for (int i = 0; i < n; i++) residual[i] = capacity[i].clone();
        int[] height = new int[n];
        int[] excess = new int[n];
        boolean[] active = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        height[s] = n;

        // Saturate every edge leaving the source
        for (int v = 0; v < n; v++) {
            if (residual[s][v] > 0) {
                excess[v] += residual[s][v];
                residual[v][s] += residual[s][v];
                residual[s][v] = 0;
                if (v != s && v != t && !active[v]) {
                    active[v] = true;
                    queue.add(v);
                }
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            active[u] = false;
            // Discharge u: push on admissible edges, relabel when stuck
            while (excess[u] > 0) {
                boolean moved = false;
                for (int v = 0; v < n && excess[u] > 0; v++) {
                    if (residual[u][v] > 0 && height[u] == height[v] + 1) {
                        int amount = Math.min(excess[u], residual[u][v]);
                        residual[u][v] -= amount;
                        residual[v][u] += amount;
                        excess[u] -= amount;
                        excess[v] += amount;
                        if (v != s && v != t && !active[v] && excess[v] > 0) {
                            active[v] = true;
                            queue.add(v);
                        }
                        moved = true;
                    }
                }
                if (!moved) {
                    int lowest = Integer.MAX_VALUE;
                    for (int v = 0; v < n; v++) {
                        if (residual[u][v] > 0) lowest = Math.min(lowest, height[v]);
                    }
                    height[u] = lowest + 1;
                }
            }
        }
        return excess[t];
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] capacity = new int[n][n];
        int[][] edges = {
            {0, 1, 16}, {0, 2, 13},
            {1, 3, 12},
            {2, 1, 4}, {2, 4, 14},
            {3, 2, 9}, {3, 5, 20},
            {4, 3, 7}, {4, 5, 4}
        };
        for (int[] e : edges) capacity[e[0]][e[1]] = e[2];
        System.out.println("Max flow: " + pushRelabel(n, capacity, 0, 5));
    }
}
