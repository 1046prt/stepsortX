// Stepsort · Johnson's Algorithm
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/johnson-algorithm

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static final long INF = Long.MAX_VALUE / 4;

    static class DirectedEdge {
        int u, v;
        long w;
        DirectedEdge(int u, int v, long w) { this.u = u; this.v = v; this.w = w; }
    }

    static long[] bellmanFord(int n, List<DirectedEdge> edges, int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;
        for (int round = 0; round < n; round++) {  // at most n rounds of relaxation
            boolean changed = false;
            for (DirectedEdge e : edges) {
                if (dist[e.u] < INF && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                    changed = true;
                }
            }
            if (!changed) break;
        }
        for (DirectedEdge e : edges)
            if (dist[e.u] < INF && dist[e.u] + e.w < dist[e.v])
                return null;  // negative cycle detected
        return dist;
    }

    static long[] dijkstra(int n, List<List<long[]>> adj, int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[source] = 0;
        pq.add(new long[]{0L, source});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int u = (int) top[1];
            if (top[0] > dist[u]) continue;
            for (long[] e : adj.get(u)) {
                int v = (int) e[0];
                if (top[0] + e[1] < dist[v]) {
                    dist[v] = top[0] + e[1];
                    pq.add(new long[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    static long[][] johnson(int n, List<DirectedEdge> edges) {
        // Virtual vertex n with 0-weight arcs feeds Bellman-Ford potentials
        List<DirectedEdge> extended = new ArrayList<>(edges);
        for (int v = 0; v < n; v++) extended.add(new DirectedEdge(n, v, 0));
        long[] h = bellmanFord(n + 1, extended, n);
        if (h == null) return null;
        List<List<long[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (DirectedEdge e : edges)
            adj.get(e.u).add(new long[]{e.v, e.w + h[e.u] - h[e.v]});  // reweighted >= 0
        long[][] result = new long[n][n];
        for (int s = 0; s < n; s++) {
            long[] dist = dijkstra(n, adj, s);
            for (int v = 0; v < n; v++)
                result[s][v] = dist[v] >= INF ? INF : dist[v] - h[s] + h[v];  // undo reweighting
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        List<DirectedEdge> edges = new ArrayList<>(Arrays.asList(
            new DirectedEdge(0, 1, 3), new DirectedEdge(0, 2, 8), new DirectedEdge(0, 4, -4),
            new DirectedEdge(1, 3, 1), new DirectedEdge(1, 4, 7),
            new DirectedEdge(2, 1, 4),
            new DirectedEdge(3, 0, 2), new DirectedEdge(3, 2, -5),
            new DirectedEdge(4, 3, 6)));
        long[][] dist = johnson(n, edges);
        if (dist == null) {
            System.out.println("Graph contains a negative weight cycle");
            return;
        }
        System.out.println("All-pairs shortest path distances:");
        for (int u = 0; u < n; u++) {
            StringBuilder row = new StringBuilder();
            for (int v = 0; v < n; v++) {
                if (v > 0) row.append(" ");
                row.append(dist[u][v] >= INF ? "inf" : String.valueOf(dist[u][v]));
            }
            System.out.println(row.toString());
        }
    }
}
