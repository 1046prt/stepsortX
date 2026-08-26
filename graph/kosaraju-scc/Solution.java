// sortsort · Kosaraju's SCC
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kosaraju-scc

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Main {
    // First pass on G: record vertices by DFS finish time
    static void finishOrder(List<List<Integer>> g, int v, boolean[] visited, Deque<Integer> order) {
        visited[v] = true;
        for (int u : g.get(v)) {
            if (!visited[u]) finishOrder(g, u, visited, order);
        }
        order.push(v);
    }

    // Second pass on reversed G: gather one component
    static void collectComponent(List<List<Integer>> g, int v, boolean[] visited, List<Integer> comp) {
        visited[v] = true;
        comp.add(v);
        for (int u : g.get(v)) {
            if (!visited[u]) collectComponent(g, u, visited, comp);
        }
    }

    static List<List<Integer>> kosaraju(int n, int[][] edges) {
        List<List<Integer>> g = new ArrayList<>();
        List<List<Integer>> rg = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
            rg.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            rg.get(e[1]).add(e[0]);
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> order = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (!visited[v]) finishOrder(g, v, visited, order);
        }
        Arrays.fill(visited, false);
        List<List<Integer>> components = new ArrayList<>();
        while (!order.isEmpty()) {  // decreasing finish time
            int v = order.pop();
            if (!visited[v]) {
                List<Integer> comp = new ArrayList<>();
                collectComponent(rg, v, visited, comp);
                Collections.sort(comp);
                components.add(comp);
            }
        }
        return components;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}};
        System.out.println("Strongly connected components:");
        for (List<Integer> comp : kosaraju(5, edges)) {
            System.out.println(comp);
        }
    }
}
