// Stepsort · Dijkstra's
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dijkstra

import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;

    static long[] dijkstra(int numVertices, List<List<int[]>> graph, int source) {
        long[] dist = new long[numVertices];
        Arrays.fill(dist, INF);
        dist[source] = 0;
        // Heap entries are [distance, vertex]
        PriorityQueue<long[]> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[]{0, source});

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0];
            int node = (int) top[1];
            if (d > dist[node]) continue;  // stale queue entry
            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];
                if (d + weight < dist[neighbor]) {
                    dist[neighbor] = d + weight;
                    pq.add(new long[]{dist[neighbor], neighbor});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int numVertices = 5;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) graph.add(new ArrayList<>());

        // Undirected weighted graph with 5 vertices (0..4)
        int[][] edges = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 5}, {2, 3, 8}, {3, 4, 3}};
        for (int[] e : edges) {
            graph.get(e[0]).add(new int[]{e[1], e[2]});
            graph.get(e[1]).add(new int[]{e[0], e[2]});
        }

        long[] dist = dijkstra(numVertices, graph, 0);
        System.out.println("Shortest distances from vertex 0:");
        for (int vertex = 0; vertex < numVertices; vertex++) {
            String label = dist[vertex] >= INF ? "INF" : String.valueOf(dist[vertex]);
            System.out.println("  vertex " + vertex + ": " + label);
        }
    }
}
