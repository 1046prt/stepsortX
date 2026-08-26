// sortsort · Karger's Min Cut
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-karger-min-cut

import java.util.ArrayList;
import java.util.List;

public class Main {
    static java.util.Random rand = new java.util.Random(42);

    static int findRoot(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    // One contraction trial; assumes a connected graph.
    static int minCutOnce(int vertexCount, List<int[]> edges) {
        int[] parent = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) parent[i] = i;
        List<int[]> work = new ArrayList<>(edges);
        int components = vertexCount;
        while (components > 2) {
            int[] edge = work.remove(rand.nextInt(work.size()));
            int rootU = findRoot(parent, edge[0]);
            int rootV = findRoot(parent, edge[1]);
            if (rootU == rootV) continue;  // self-loop: already contracted
            parent[rootV] = rootU;
            components--;
        }
        int cut = 0;
        for (int[] edge : work)
            if (findRoot(parent, edge[0]) != findRoot(parent, edge[1]))
                cut++;
        return cut;
    }

    public static void main(String[] args) {
        int[][] rawEdges = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}};
        List<int[]> edges = new ArrayList<>();
        for (int[] edge : rawEdges) edges.add(edge);
        final int trials = 200;
        int best = Integer.MAX_VALUE;
        for (int t = 0; t < trials; t++)
            best = Math.min(best, minCutOnce(4, edges));
        System.out.println("minimum cut found over " + trials + " trials: " + best);
    }
}
