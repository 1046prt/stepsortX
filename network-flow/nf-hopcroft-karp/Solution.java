// sortsort · Hopcroft-Karp
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-hopcroft-karp

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
    static List<List<Integer>> adj = new ArrayList<>();
    static int[] matchLeft, matchRight, layerDist;

    static boolean bfsLayers() {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int u = 1; u < matchLeft.length; u++) {
            if (matchLeft[u] == 0) {
                layerDist[u] = 0;
                queue.add(u);
            } else {
                layerDist[u] = Integer.MAX_VALUE;
            }
        }
        boolean foundFree = false;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                int w = matchRight[v];
                if (w == 0) foundFree = true;
                else if (layerDist[w] == Integer.MAX_VALUE) {
                    layerDist[w] = layerDist[u] + 1;
                    queue.add(w);
                }
            }
        }
        return foundFree;
    }

    static boolean dfsAugment(int u) {
        for (int v : adj.get(u)) {
            int w = matchRight[v];
            if (w == 0 || (layerDist[w] == layerDist[u] + 1 && dfsAugment(w))) {
                matchLeft[u] = v;
                matchRight[v] = u;
                return true;
            }
        }
        layerDist[u] = Integer.MAX_VALUE;
        return false;
    }

    public static void main(String[] args) {
        int n = 4;
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        adj.get(1).add(1); adj.get(1).add(2);
        adj.get(2).add(1); adj.get(2).add(3);
        adj.get(3).add(2); adj.get(3).add(4);
        adj.get(4).add(3);
        matchLeft = new int[n + 1];
        matchRight = new int[n + 1];
        layerDist = new int[n + 1];

        int matching = 0;
        while (bfsLayers()) {
            for (int u = 1; u <= n; u++) {
                if (matchLeft[u] == 0 && dfsAugment(u)) matching++;
            }
        }
        System.out.println("Maximum matching size: " + matching);
        for (int u = 1; u <= n; u++) {
            if (matchLeft[u] != 0) {
                System.out.println("Left " + u + " matched with Right " + matchLeft[u]);
            }
        }
    }
}
