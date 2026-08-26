// Stepsort · Tree Diameter
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-diameter

import java.util.*;

public class Main {

    static int[] dist;
    static int[] parent;

    static int bfsFarthest(List<List<Integer>> adj, int src) {
        int n = adj.size();
        dist = new int[n];
        parent = new int[n];
        Arrays.fill(dist, -1);
        Arrays.fill(parent, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        dist[src] = 0;
        int far = src;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                    if (dist[v] > dist[far]) far = v;
                    queue.add(v);
                }
            }
        }
        return far;
    }

    public static void main(String[] args) {
        int n = 7;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {4, 5}, {1, 6}};
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int a = bfsFarthest(adj, 0);   // pass 1: arbitrary start
        int b = bfsFarthest(adj, a);   // pass 2: from farthest node

        System.out.println("Diameter length: " + dist[b]);
        System.out.println("Endpoints: " + a + " and " + b);

        List<Integer> path = new ArrayList<>();
        for (int cur = b; cur != -1; cur = parent[cur]) path.add(cur);
        Collections.reverse(path);

        System.out.print("Path:");
        for (int x : path) System.out.print(" " + x);
        System.out.println();
    }
}
