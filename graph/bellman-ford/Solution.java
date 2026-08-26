// sortsort · Bellman-Ford
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bellman-ford

import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE / 2;

    // Returns null if a negative cycle is reachable from the source.
    static long[] bellmanFord(int numVertices, int[][] edges, int source) {
        long[] dist = new long[numVertices];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Relax every edge V-1 times
        for (int pass = 0; pass < numVertices - 1; pass++) {
            boolean changed = false;
            for (int[] e : edges) {
                if (dist[e[0]] + e[2] < dist[e[1]]) {
                    dist[e[1]] = dist[e[0]] + e[2];
                    changed = true;
                }
            }
            if (!changed) break;
        }

        // One more improving pass means a negative cycle is reachable
        for (int[] e : edges) {
            if (dist[e[0]] + e[2] < dist[e[1]]) return null;
        }
        return dist;
    }

    public static void main(String[] args) {
        // Directed weighted graph with 5 vertices (0..4)
        int[][] edges = {{0, 1, 4}, {0, 2, 5}, {1, 2, -3}, {1, 3, 6}, {2, 3, 4}, {3, 4, 2}};

        long[] dist = bellmanFord(5, edges, 0);
        if (dist == null) {
            System.out.println("Negative cycle detected");
            return;
        }
        System.out.println("Shortest distances from vertex 0:");
        for (int vertex = 0; vertex < dist.length; vertex++) {
            String label = dist[vertex] >= INF ? "INF" : String.valueOf(dist[vertex]);
            System.out.println("  vertex " + vertex + ": " + label);
        }
    }
}
