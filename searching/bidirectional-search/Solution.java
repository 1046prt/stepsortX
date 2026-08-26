// Stepsort · Bidirectional Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bidirectional-search

import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    static void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    // Expand one whole BFS level; return a node the other side saw, or -1.
    static int expandLevel(Deque<Integer> q, Map<Integer, Integer> mine,
                           Map<Integer, Integer> theirs) {
        int level = q.size();
        for (int k = 0; k < level; k++) {
            int node = q.poll();
            for (int nxt : graph.getOrDefault(node, Collections.emptyList())) {
                if (!mine.containsKey(nxt)) {
                    mine.put(nxt, node);
                    q.offer(nxt);
                    if (theirs.containsKey(nxt)) return nxt;
                }
            }
        }
        return -1;
    }

    // Stitch the source-to-meet and meet-to-target halves together.
    static List<Integer> buildPath(Map<Integer, Integer> srcParent,
                                   Map<Integer, Integer> dstParent, int meet) {
        List<Integer> path = new ArrayList<>();
        for (int v = meet; v != -1; v = srcParent.get(v)) path.add(v);
        Collections.reverse(path);
        for (int v = dstParent.get(meet); v != -1; v = dstParent.get(v)) path.add(v);
        return path;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 7}};
        for (int[] e : edges) addEdge(e[0], e[1]);

        int source = 0, target = 7;
        Map<Integer, Integer> srcParent = new HashMap<>();
        Map<Integer, Integer> dstParent = new HashMap<>();
        srcParent.put(source, -1);
        dstParent.put(target, -1);
        Deque<Integer> sq = new ArrayDeque<>();
        Deque<Integer> dq = new ArrayDeque<>();
        sq.offer(source);
        dq.offer(target);
        int meet = -1;
        while (meet == -1 && !sq.isEmpty() && !dq.isEmpty()) {
            if (sq.size() <= dq.size()) meet = expandLevel(sq, srcParent, dstParent);
            else meet = expandLevel(dq, dstParent, srcParent);
        }
        System.out.println("meeting node: " + meet);
        System.out.println("path found: " + (meet != -1));
        if (meet != -1) {
            System.out.println("path: " + buildPath(srcParent, dstParent, meet));
        }
    }
}
