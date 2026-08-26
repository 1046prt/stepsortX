// Stepsort · Tarjan's SCC
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tarjan-scc

import java.util.*;

public class Main {
    static int counter;
    static int[] index, low;
    static boolean[] onStack;
    static Deque<Integer> stack;

    static void strongConnect(int u, List<List<Integer>> adj,
                              List<List<Integer>> components) {
        index[u] = low[u] = counter++;
        stack.push(u);
        onStack[u] = true;
        for (int v : adj.get(u)) {
            if (index[v] == -1) {
                strongConnect(v, adj, components);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], index[v]);
            }
        }
        if (low[u] == index[u]) {  // u is the root of an SCC
            List<Integer> component = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                onStack[w] = false;
                component.add(w);
            } while (w != u);
            Collections.sort(component);
            components.add(component);
        }
    }

    static List<List<Integer>> tarjanScc(List<List<Integer>> adj) {
        int V = adj.size();
        counter = 0;
        index = new int[V];
        low = new int[V];
        onStack = new boolean[V];
        stack = new ArrayDeque<>();
        Arrays.fill(index, -1);
        List<List<Integer>> components = new ArrayList<>();
        for (int u = 0; u < V; u++)
            if (index[u] == -1) strongConnect(u, adj, components);
        return components;
    }

    public static void main(String[] args) {
        int[][] edges =
            {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3}, {4, 5},
             {6, 7}, {7, 5}, {7, 6}};
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 8; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);

        for (List<Integer> component : tarjanScc(adj))
            System.out.println("SCC: " + component);
    }
}
