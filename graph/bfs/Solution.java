// Stepsort · BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bfs

import java.util.*;

public class Main {
    static List<Integer> bfs(List<List<Integer>> graph, int source) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> order = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return order;
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

        System.out.println("BFS visit order from vertex 0: " + bfs(graph, 0));
    }
}
