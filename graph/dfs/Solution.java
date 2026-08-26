// Stepsort · DFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dfs

import java.util.*;

public class Main {
    // Recursive depth-first exploration
    static void explore(List<List<Integer>> graph, int node,
                        boolean[] visited, List<Integer> order) {
        visited[node] = true;
        order.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                explore(graph, neighbor, visited, order);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) graph.add(new ArrayList<>());

        // Undirected graph with 6 vertices (0..5)
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 5}};
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        List<Integer> order = new ArrayList<>();
        explore(graph, 0, new boolean[vertices], order);
        System.out.println("DFS visit order from vertex 0: " + order);
    }
}
