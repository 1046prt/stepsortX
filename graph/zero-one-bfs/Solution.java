// sortsort · 0-1 BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/zero-one-bfs

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1,0},{0,2,1},{1,3,0},{2,3,0},{2,4,1},{3,5,1},{4,5,0}};
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        Deque<Integer> dq = new ArrayDeque<>(List.of(0));
        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            for (int[] nb : adj.get(u)) {
                int v = nb[0], w = nb[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    if (w == 0) dq.addFirst(v); else dq.addLast(v);
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }
}
