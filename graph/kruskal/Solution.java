// Stepsort · Kruskal's MST
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kruskal

import java.util.*;

public class Main {
    static int[] parent;

    static int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];  // path compression
            x = parent[x];
        }
        return x;
    }

    static boolean union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootA] = rootB;
        return true;
    }

    public static void main(String[] args) {
        int numVertices = 4;
        // Undirected weighted graph with 4 vertices (0..3)
        int[][] edges = {{0, 1, 4}, {0, 2, 3}, {1, 2, 1}, {1, 3, 2}, {2, 3, 5}};
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));

        parent = new int[numVertices];
        for (int i = 0; i < numVertices; i++) parent[i] = i;

        long totalWeight = 0;
        List<String> chosen = new ArrayList<>();
        for (int[] e : edges) {
            if (union(e[0], e[1])) {  // skip edges that would form a cycle
                totalWeight += e[2];
                chosen.add(e[0] + " - " + e[1] + " (weight " + e[2] + ")");
            }
        }

        System.out.println("Kruskal MST total weight: " + totalWeight);
        System.out.println("Chosen edges:");
        for (String edge : chosen) System.out.println("  " + edge);
    }
}
