// sortsort · Prim's MST
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/prim

import java.util.*;

public class Main {
    // Heap entry: edge weight, vertex, parent
    static class Node implements Comparable<Node> {
        int weight, vertex, parent;

        Node(int weight, int vertex, int parent) {
            this.weight = weight;
            this.vertex = vertex;
            this.parent = parent;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static void prim(int numVertices, List<List<int[]>> graph) {
        boolean[] visited = new boolean[numVertices];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long totalWeight = 0;
        List<String> mstEdges = new ArrayList<>();

        pq.add(new Node(0, 0, -1));  // start at vertex 0 with virtual parent -1
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.vertex]) continue;  // stale queue entry
            visited[current.vertex] = true;
            totalWeight += current.weight;
            if (current.parent != -1) {
                mstEdges.add(current.parent + " - " + current.vertex
                    + " (weight " + current.weight + ")");
            }
            for (int[] edge : graph.get(current.vertex)) {
                if (!visited[edge[1]]) {
                    pq.add(new Node(edge[0], edge[1], current.vertex));
                }
            }
        }

        System.out.println("Prim MST total weight: " + totalWeight);
        System.out.println("MST edges:");
        for (String edge : mstEdges) System.out.println("  " + edge);
    }

    public static void main(String[] args) {
        int numVertices = 5;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) graph.add(new ArrayList<>());

        // Undirected weighted graph with 5 vertices (0..4)
        int[][] edges = {{0, 1, 2}, {0, 3, 6}, {1, 2, 3},
                         {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}};
        for (int[] e : edges) {
            graph.get(e[0]).add(new int[]{e[2], e[1]});
            graph.get(e[1]).add(new int[]{e[2], e[0]});
        }

        prim(numVertices, graph);
    }
}
