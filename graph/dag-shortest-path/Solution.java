// sortsort · DAG Shortest Path
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dag-shortest-path

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1,3},{0,2,2},{1,3,4},{2,3,-2},{2,4,5},{3,5,1},{4,5,-1}};
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[] indeg = new int[n];
        for (int[] e : edges) { adj.get(e[0]).add(new int[]{e[1], e[2]}); indeg[e[1]]++; }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] nb : adj.get(u)) if (--indeg[nb[0]] == 0) q.add(nb[0]);
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        for (int u : topo) {
            if (dist[u] == Long.MAX_VALUE) continue;
            for (int[] nb : adj.get(u))
                dist[nb[0]] = Math.min(dist[nb[0]], dist[u] + nb[1]);
        }
        System.out.println(Arrays.toString(dist));
    }
}
