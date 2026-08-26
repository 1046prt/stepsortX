// sortsort · Edmonds-Karp
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-edmonds-karp

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int edmondsKarp(int n, int[][] residual, int s, int t) {
        int maxFlow = 0;
        while (true) {
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            parent[s] = s;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(s);
            while (!queue.isEmpty() && parent[t] == -1) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && residual[u][v] > 0) {
                        parent[v] = u;
                        queue.add(v);
                    }
                }
            }
            if (parent[t] == -1) break;

            int bottleneck = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                bottleneck = Math.min(bottleneck, residual[parent[v]][v]);
            }
            for (int v = t; v != s; v = parent[v]) {
                residual[parent[v]][v] -= bottleneck;
                residual[v][parent[v]] += bottleneck;
            }
            maxFlow += bottleneck;
        }
        return maxFlow;
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
        System.out.println("Max flow: " + edmondsKarp(n, capacity, 0, 5));
    }
}
