// sortsort · Ford-Fulkerson Max Flow
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/max-flow

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    // Ford-Fulkerson method; BFS picks shortest augmenting paths first
    static long fordFulkerson(long[][] capacity, int source, int sink) {
        int n = capacity.length;
        long[][] residual = new long[n][];
        for (int i = 0; i < n; i++) residual[i] = capacity[i].clone();
        long totalFlow = 0;
        while (true) {
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            parent[source] = source;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(source);
            while (!queue.isEmpty() && parent[sink] == -1) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (residual[u][v] > 0 && parent[v] == -1) {
                        parent[v] = u;
                        queue.add(v);
                    }
                }
            }
            if (parent[sink] == -1) break;  // no augmenting path remains
            long bottleneck = Long.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v])
                bottleneck = Math.min(bottleneck, residual[parent[v]][v]);
            for (int v = sink; v != source; v = parent[v]) {
                residual[parent[v]][v] -= bottleneck;
                residual[v][parent[v]] += bottleneck;
            }
            totalFlow += bottleneck;
        }
        return totalFlow;
    }

    public static void main(String[] args) {
        long[][] capacity = {
            {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
        };
        System.out.println("Max flow: " + fordFulkerson(capacity, 0, 5));
    }
}
