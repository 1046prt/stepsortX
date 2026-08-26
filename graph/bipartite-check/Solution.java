// Stepsort · Bipartite Check
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bipartite-check

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
    // BFS 2-coloring from one start vertex; false means a conflict was found
    static boolean bfsColor(List<List<Integer>> adj, int start, int[] color) {
        color[start] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (color[v] == -1) {
                    color[v] = color[u] ^ 1;  // opposite color of the neighbor
                    queue.add(v);
                } else if (color[v] == color[u]) {
                    return false;  // odd cycle makes 2-coloring impossible
                }
            }
        }
        return true;
    }

    static boolean isBipartite(int n, int[][] edges, int[] color) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        Arrays.fill(color, -1);
        for (int v = 0; v < n; v++) {
            if (color[v] == -1 && !bfsColor(adj, v, color)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {2, 3}, {2, 4}};
        int[] color = new int[n];
        if (!isBipartite(n, edges, color)) {
            System.out.println("Graph is NOT bipartite");
            return;
        }
        System.out.println("Graph is bipartite");
        StringBuilder setA = new StringBuilder("Set A:");
        StringBuilder setB = new StringBuilder("Set B:");
        for (int v = 0; v < n; v++) {
            if (color[v] == 0) setA.append(" ").append(v);
            else setB.append(" ").append(v);
        }
        System.out.println(setA);
        System.out.println(setB);
    }
}
