// Stepsort · SPFA (Shortest Path Faster)
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/spfa

import java.util.*;

public class SPFA {
    static int[] spfa(List<List<int[]>> graph, int source, int n) {
        int INF = Integer.MAX_VALUE / 2;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        boolean[] inQueue = new boolean[n];
        int[] enqueueCount = new int[n];
        dist[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        inQueue[source] = true;
        enqueueCount[source] = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;
            for (int[] edge : graph.get(u)) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    if (!inQueue[v]) {
                        queue.add(v);
                        inQueue[v] = true;
                        if (++enqueueCount[v] > n) return new int[]{}; // negative cycle
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        int[][] edges = {{0,1,6},{0,2,4},{1,2,2},{1,3,5},{2,3,-3},{2,4,1},{3,4,2}};
        for (int[] e : edges) g.get(e[0]).add(new int[]{e[1], e[2]});
        System.out.println(Arrays.toString(spfa(g, 0, n)));
    }
}
