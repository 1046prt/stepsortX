// sortsort · Biconnected Components
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/biconnected-components

import java.util.*;

public class Main {
    static List<List<int[]>> adj = new ArrayList<>();
    static int[] disc, low;
    static Deque<int[]> stack = new ArrayDeque<>();
    static List<List<Integer>> comps = new ArrayList<>();
    static int timer = 0;

    static void dfs(int u, int pe) {
        disc[u] = low[u] = ++timer;
        for (int[] nb : adj.get(u)) {
            int v = nb[0], ei = nb[1];
            if (ei == pe) continue;
            if (disc[v] == -1) {
                stack.push(new int[]{u, v});
                dfs(v, ei);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] >= disc[u]) {
                    Set<Integer> nodes = new TreeSet<>();
                    int[] top = stack.peek();
                    while (!(top[0] == u && top[1] == v)) {
                        int[] e = stack.pop();
                        nodes.add(e[0]); nodes.add(e[1]);
                        top = stack.peek();
                    }
                    int[] e = stack.pop();
                    nodes.add(e[0]); nodes.add(e[1]);
                    comps.add(new ArrayList<>(nodes));
                }
            } else if (disc[v] < disc[u]) {
                stack.push(new int[]{u, v});
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[][] edges = {{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}};
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new int[]{edges[i][1], i});
            adj.get(edges[i][1]).add(new int[]{edges[i][0], i});
        }
        disc = new int[n]; low = new int[n];
        Arrays.fill(disc, -1); Arrays.fill(low, -1);
        for (int s = 0; s < n; s++) if (disc[s] == -1) dfs(s, -1);
        System.out.println(comps.size() + " biconnected components");
    }
}
