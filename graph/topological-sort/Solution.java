// Stepsort · Topological Sort
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/topological-sort

import java.util.*;

public class Main {
    // Kahn BFS method: repeatedly take vertices whose indegree hits zero
    static List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++)
            for (int v : adj.get(u)) indegree[v]++;

        Deque<Integer> ready = new ArrayDeque<>();
        for (int u = 0; u < V; u++)
            if (indegree[u] == 0) ready.addLast(u);

        List<Integer> order = new ArrayList<>();
        while (!ready.isEmpty()) {
            int u = ready.pollFirst();
            order.add(u);
            for (int v : adj.get(u))
                if (--indegree[v] == 0) ready.addLast(v);
        }
        return order;
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        int[][] edges = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        for (int[] e : edges) adj.get(e[0]).add(e[1]);

        List<Integer> order = topologicalSort(V, adj);
        System.out.println("Topological order: " + order);
        System.out.println("Valid DAG ordering: " + (order.size() == V));
    }
}
