// Stepsort · Cycle Detection
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cycle-detection-graph

import java.util.*;

public class Main {
    // Three-color DFS: white = unvisited, gray = in current stack, black = done
    static final int WHITE = 0, GRAY = 1, BLACK = 2;

    static boolean dfs(int u, List<List<Integer>> adj, int[] color) {
        color[u] = GRAY;
        for (int v : adj.get(u)) {
            if (color[v] == GRAY) return true;  // back edge into current path
            if (color[v] == WHITE && dfs(v, adj, color)) return true;
        }
        color[u] = BLACK;
        return false;
    }

    static boolean hasCycleDirected(List<List<Integer>> adj) {
        int[] color = new int[adj.size()];
        for (int u = 0; u < adj.size(); u++)
            if (color[u] == WHITE && dfs(u, adj, color)) return true;
        return false;
    }

    static List<List<Integer>> buildAdj(int[][] edges, int V) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        List<List<Integer>> cyclicGraph =
            buildAdj(new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}}, 4);
        List<List<Integer>> acyclicGraph =
            buildAdj(new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, 4);

        System.out.println("Graph 1 cyclic: " + hasCycleDirected(cyclicGraph));
        System.out.println("Graph 2 cyclic: " + hasCycleDirected(acyclicGraph));
    }
}
