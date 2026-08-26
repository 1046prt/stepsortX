// sortsort · Dinic's Algorithm
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-dinic

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
    static class Edge {
        int to, cap, rev;
        Edge(int to, int cap, int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
    }

    static List<List<Edge>> graph = new ArrayList<>();
    static int[] level, iterator;

    static void addEdge(int u, int v, int cap) {
        graph.get(u).add(new Edge(v, cap, graph.get(v).size()));
        graph.get(v).add(new Edge(u, 0, graph.get(u).size() - 1));
    }

    static boolean buildLevels(int s, int t) {
        Arrays.fill(level, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        level[s] = 0;
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge e : graph.get(u)) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    queue.add(e.to);
                }
            }
        }
        return level[t] != -1;
    }

    static int sendFlow(int u, int t, int limit) {
        if (u == t) return limit;
        while (iterator[u] < graph.get(u).size()) {
            Edge e = graph.get(u).get(iterator[u]);
            if (e.cap > 0 && level[e.to] == level[u] + 1) {
                int pushed = sendFlow(e.to, t, Math.min(limit, e.cap));
                if (pushed > 0) {
                    e.cap -= pushed;
                    graph.get(e.to).get(e.rev).cap += pushed;
                    return pushed;
                }
            }
            iterator[u]++;
        }
        return 0;
    }

    public static void main(String[] args) {
        int n = 6;
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        level = new int[n];
        iterator = new int[n];
        int[][] edges = {
            {0, 1, 16}, {0, 2, 13},
            {1, 3, 12},
            {2, 1, 4}, {2, 4, 14},
            {3, 2, 9}, {3, 5, 20},
            {4, 3, 7}, {4, 5, 4}
        };
        for (int[] e : edges) addEdge(e[0], e[1], e[2]);
        int flow = 0;
        while (buildLevels(0, 5)) {
            Arrays.fill(iterator, 0);
            int pushed;
            while ((pushed = sendFlow(0, 5, Integer.MAX_VALUE)) > 0) flow += pushed;
        }
        System.out.println("Max flow: " + flow);
    }
}
