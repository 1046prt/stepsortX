// sortsort · Centroid Decomposition
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/centroid-decomposition

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 12;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6},
                         {3, 7}, {3, 8}, {4, 9}, {5, 10}, {7, 11}};
        List<List<Integer>> adj = new ArrayList<>();
        for (int v = 0; v < n; v++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);
        List<Integer> removal = new ArrayList<>();
        while (removal.size() < n) {
            int root = 0;
            while (!alive[root]) root++;
            int[] par = new int[n];
            Arrays.fill(par, -1);
            boolean[] visited = new boolean[n];
            visited[root] = true;
            List<Integer> preorder = new ArrayList<>();
            List<Integer> stack = new ArrayList<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                int v = stack.remove(stack.size() - 1);
                preorder.add(v);
                for (int u : adj.get(v)) {
                    if (alive[u] && !visited[u]) {
                        visited[u] = true;
                        par[u] = v;
                        stack.add(u);
                    }
                }
            }
            int[] size = new int[n];
            Arrays.fill(size, 1);
            for (int i = preorder.size() - 1; i >= 0; i--) {
                int v = preorder.get(i);
                if (par[v] != -1) size[par[v]] += size[v];
            }
            int total = size[root], centroid = -1;
            for (int v : preorder) {
                int worst = total - size[v];
                for (int u : adj.get(v)) {
                    if (alive[u] && u != par[v] && visited[u])
                        worst = Math.max(worst, size[u]);
                }
                if (worst <= total / 2) { centroid = v; break; }
            }
            removal.add(centroid);
            alive[centroid] = false;
        }
        StringBuilder sb = new StringBuilder("centroid removal order:");
        for (int v : removal) sb.append(" ").append(v);
        System.out.println(sb);
    }
}
