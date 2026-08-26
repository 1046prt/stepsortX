// Stepsort · Eulerian Path/Circuit
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/eulerian-path

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Main {
    // Hierholzer construction over an undirected multigraph
    static List<Integer> eulerianPath(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();  // entries: {neighbor, edgeId}
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new int[]{edges[i][1], i});
            adj.get(edges[i][1]).add(new int[]{edges[i][0], i});
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        List<Integer> odd = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if (degree[v] % 2 == 1) odd.add(v);
        }
        if (odd.size() != 0 && odd.size() != 2) return null;  // impossible
        int start = odd.isEmpty() ? edges[0][0] : odd.get(0);
        boolean[] used = new boolean[edges.length];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        List<Integer> path = new ArrayList<>();
        while (!stack.isEmpty()) {
            int v = stack.peek();
            List<int[]> options = adj.get(v);
            // Lazily discard already-used edges at this vertex
            while (!options.isEmpty() && used[options.get(options.size() - 1)[1]]) {
                options.remove(options.size() - 1);
            }
            if (options.isEmpty()) {
                path.add(v);
                stack.pop();
            } else {
                int[] choice = options.remove(options.size() - 1);
                used[choice[1]] = true;
                stack.push(choice[0]);
            }
        }
        if (path.size() != edges.length + 1) return null;  // disconnected edges
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {0, 3}, {3, 4}, {4, 0}};
        List<Integer> path = eulerianPath(5, edges);
        if (path == null) {
            System.out.println("No Eulerian path exists");
        } else {
            System.out.println("Eulerian path: " + path);
        }
    }
}
