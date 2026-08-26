// Stepsort · Bridges & Articulation Points
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bridges-articulation

import java.util.*;

public class Main {
    static int timer;
    static int[] disc, low;
    static List<int[]> bridges;
    static TreeSet<Integer> articulation;

    static void dfs(int u, int parent, List<List<Integer>> adj) {
        disc[u] = low[u] = timer++;
        int children = 0;
        for (int v : adj.get(u)) {
            if (v == parent) continue;
            if (disc[v] == -1) {
                children++;
                dfs(v, u, adj);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) bridges.add(new int[]{u, v});  // no way back
                if (parent != -1 && low[v] >= disc[u])
                    articulation.add(u);  // subtree cannot bypass u
            } else {
                low[u] = Math.min(low[u], disc[v]);  // back edge
            }
        }
        if (parent == -1 && children > 1)
            articulation.add(u);  // root with separated subtrees
    }

    public static void main(String[] args) {
        int V = 7;
        int[][] edgeList =
            {{0, 1}, {0, 2}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {4, 6}};
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edgeList) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        timer = 0;
        disc = new int[V];
        low = new int[V];
        Arrays.fill(disc, -1);
        bridges = new ArrayList<>();
        articulation = new TreeSet<>();
        dfs(0, -1, adj);

        System.out.print("Bridges:");
        for (int[] e : bridges)
            System.out.print(" (" + e[0] + "," + e[1] + ")");
        System.out.println();
        System.out.println("Articulation points: " + articulation);
    }
}
