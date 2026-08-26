// Stepsort · Depth-Limited Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/depth-limited-search

import java.util.*;

public class Main {
    // DFS capped at the depth limit; fills path and returns true on success.
    static boolean dls(Map<Integer, List<Integer>> graph, int node, int target,
                       int limit, List<Integer> path) {
        path.add(node);
        if (node == target) return true;
        if (path.size() - 1 < limit) {
            for (int nxt : graph.getOrDefault(node, Collections.emptyList())) {
                if (!path.contains(nxt) && dls(graph, nxt, target, limit, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(3, 4));
        graph.put(2, Arrays.asList(5, 6));
        graph.put(3, Arrays.asList(7));
        graph.put(4, Arrays.asList(7));
        graph.put(5, new ArrayList<>());
        graph.put(6, new ArrayList<>());
        graph.put(7, new ArrayList<>());

        List<Integer> path = new ArrayList<>();
        if (dls(graph, 0, 6, 2, path)) {
            System.out.println("limit 2 to node 6, path: " + path);
        } else {
            System.out.println("node 6 not reached within limit 2");
        }
        path.clear();
        if (dls(graph, 0, 7, 1, path)) {
            System.out.println("limit 1 to node 7, path: " + path);
        } else {
            System.out.println("node 7 not reached within limit 1");
        }
    }
}
