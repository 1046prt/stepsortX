// sortsort · Graph Coloring
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/graph-coloring

import java.util.*;

public class Main {
    // Assign each vertex the smallest color not used by its colored neighbors
    static int[] greedyColoring(int V, List<List<Integer>> adj) {
        int[] result = new int[V];
        Arrays.fill(result, -1);
        for (int u = 0; u < V; u++) {
            Set<Integer> neighborColors = new HashSet<>();
            for (int v : adj.get(u))
                if (result[v] != -1) neighborColors.add(result[v]);
            int color = 0;
            while (neighborColors.contains(color)) color++;
            result[u] = color;
        }
        return result;
    }

    public static void main(String[] args) {
        int V = 5;
        // Undirected triangle 0-1-2 plus tail 3-4
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(Arrays.asList(1, 2));
        adj.add(Arrays.asList(0, 2, 3));
        adj.add(Arrays.asList(0, 1, 3));
        adj.add(Arrays.asList(1, 2, 4));
        adj.add(Arrays.asList(3));

        int[] colors = greedyColoring(V, adj);
        for (int v = 0; v < V; v++)
            System.out.println("Vertex " + v + " -> color " + colors[v]);

        int max = 0;
        for (int c : colors) max = Math.max(max, c);
        System.out.println("Total colors used: " + (max + 1));
    }
}
