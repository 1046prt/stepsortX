// Stepsort · Capacity Scaling Max Flow
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/capacity-scaling

import java.util.*;

public class Main {
    static int n = 6;
    static long[][] cap, flw;
    static boolean[] vis;

    static long dfs(int u, int sink, long limit) {
        if (u == sink) return limit;
        vis[u] = true;
        for (int v = 0; v < n; v++) {
            if (vis[v]) continue;
            long residual = cap[u][v] - flw[u][v];
            if (residual >= limit) {
                long pushed = dfs(v, sink, Math.min(limit, residual));
                if (pushed > 0) {
                    flw[u][v] += pushed;
                    flw[v][u] -= pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        cap = new long[n][n]; flw = new long[n][n];
        int[][] caps = {{0,1,16},{0,2,13},{1,3,12},{2,1,4},{3,2,9},{2,4,14},{4,3,7},{3,5,20},{4,5,4}};
        for (int[] e : caps) cap[e[0]][e[1]] = e[2];

        int maxCap = 16, delta = 1, total = 0;
        while (delta * 2 <= maxCap) delta *= 2;

        List<String> logLines = new ArrayList<>();
        while (delta >= 1) {
            while (true) {
                vis = new boolean[n];
                long pushed = dfs(0, 5, delta);
                if (pushed == 0) break;
                total += pushed;
                logLines.add("delta=" + delta + ": +" + pushed);
            }
            delta /= 2;
        }
        logLines.forEach(System.out::println);
        System.out.println("max flow: " + total);   // 23
    }
}
