// Stepsort · Min-Cost Max Flow
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-min-cost-flow

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
    static class Edge {
        int to, cap, cost, rev;
        Edge(int to, int cap, int cost, int rev) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.rev = rev;
        }
    }

    static List<List<Edge>> graph = new ArrayList<>();
    static int[] prevNode, prevEdge;

    static void addEdge(int u, int v, int cap, int cost) {
        graph.get(u).add(new Edge(v, cap, cost, graph.get(v).size()));
        graph.get(v).add(new Edge(u, 0, -cost, graph.get(u).size() - 1));
    }

    static int[] shortestPaths(int s) {
        int n = graph.size(), INF = Integer.MAX_VALUE;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        Arrays.fill(prevNode, -1);
        Arrays.fill(prevEdge, -1);
        boolean[] inQueue = new boolean[n];
        dist[s] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;
            List<Edge> edges = graph.get(u);
            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);
                if (e.cap > 0 && dist[u] != INF && dist[u] + e.cost < dist[e.to]) {
                    dist[e.to] = dist[u] + e.cost;
                    prevNode[e.to] = u;
                    prevEdge[e.to] = i;
                    if (!inQueue[e.to]) {
                        inQueue[e.to] = true;
                        queue.add(e.to);
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 4, s = 0, t = 3, maxPush = 100;
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        prevNode = new int[n];
        prevEdge = new int[n];
        int[][] edges = {
            {0, 1, 2, 2},
            {0, 2, 1, 3},
            {1, 3, 1, 4},
            {1, 2, 1, 1},
            {2, 3, 2, 1}
        };
        for (int[] e : edges) addEdge(e[0], e[1], e[2], e[3]);

        int flow = 0, totalCost = 0;
        while (flow < maxPush) {
            int[] dist = shortestPaths(s);
            if (dist[t] == Integer.MAX_VALUE) break;
            int push = maxPush - flow;
            for (int v = t; v != s; v = prevNode[v]) {
                push = Math.min(push, graph.get(prevNode[v]).get(prevEdge[v]).cap);
            }
            for (int v = t; v != s; v = prevNode[v]) {
                Edge e = graph.get(prevNode[v]).get(prevEdge[v]);
                e.cap -= push;
                graph.get(v).get(e.rev).cap += push;
            }
            flow += push;
            totalCost += push * dist[t];
        }
        System.out.println("Flow sent: " + flow);
        System.out.println("Total cost: " + totalCost);
    }
}
