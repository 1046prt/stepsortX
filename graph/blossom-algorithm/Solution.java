// Stepsort · Blossom Algorithm (Edmonds)
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/blossom-algorithm

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 7;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[][] e = {{0,1},{1,2},{2,3},{3,4},{4,0},{4,5},{5,6}};
        for (int[] p : e) { adj.get(p[0]).add(p[1]); adj.get(p[1]).add(p[0]); }

        int[] match = new int[n];
        Arrays.fill(match, -1);
        for (int u = 0; u < n; u++) {
            if (match[u] != -1) continue;
            for (int v : adj.get(u)) {
                if (match[v] == -1) { match[u] = v; match[v] = u; break; }
            }
        }
        System.out.println("greedy matching done");
        System.out.println("exposed: " +
            IntStream.range(0, n).filter(v -> match[v] == -1).boxed().toList());
    }
}
