// sortsort · Iterative Deepening DFS
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/iterative-deepening

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

    // Re-run depth-limited search with limits 0..maxLimit until found.
    static int iterativeDeepening(Map<Integer, List<Integer>> graph, int start,
                                  int target, int maxLimit, List<Integer> path) {
        for (int limit = 0; limit <= maxLimit; limit++) {
            path.clear();
            if (dls(graph, start, target, limit, path)) return limit;
        }
        path.clear();
        return -1;
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
        int depth = iterativeDeepening(graph, 0, 7, 4, path);
        System.out.println("target 7 found at depth " + depth + ", path: " + path);
        depth = iterativeDeepening(graph, 0, 5, 4, path);
        System.out.println("target 5 found at depth " + depth + ", path: " + path);
    }
}
