// sortsort · Offline Dynamic Connectivity
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/offline-dynamic-connectivity

import java.util.*;

public class Main {
    static int[] p, sz;
    static List<Integer> log = new ArrayList<>();

    static int find(int x) { while (p[x] != x) x = p[x]; return x; }

    static void unite(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) { log.add(-1); return; }
        if (sz[ra] < sz[rb]) { int t = ra; ra = rb; rb = t; }
        p[rb] = ra; sz[ra] += sz[rb]; log.add(rb);
    }

    static void rollback(int mark) {
        while (log.size() > mark) {
            int rb = log.remove(log.size() - 1);
            if (rb != -1) { sz[p[rb]] -= sz[rb]; p[rb] = rb; }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        p = new int[n]; sz = new int[n];
        for (int i = 0; i < n; i++) { p[i] = i; sz[i] = 1; }
        int[][] lifespan = {{0,1,0,6},{1,2,0,3},{2,3,2,6},{0,3,1,4},{3,4,4,6}};

        for (int t = 0; t < 6; t++) {
            int mark = log.size();
            for (int[] e : lifespan)
                if (e[2] <= t && t < e[3]) unite(e[0], e[1]);
            Set<Integer> roots = new HashSet<>();
            for (int v = 0; v < n; v++) roots.add(find(v));
            System.out.println("t=" + t + ": " + roots.size() + " components");
            rollback(mark);
        }
    }
}
