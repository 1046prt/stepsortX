// sortsort · Boruvka's MST
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boruvka-mst

import java.util.*;

public class Main {
    static int[] p;

    static int find(int x) {
        while (p[x] != x) { p[x] = p[p[x]]; x = p[x]; }
        return x;
    }

    public static void main(String[] args) {
        int n = 6;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int[][] e = {{0,1,4},{0,2,2},{1,2,1},{1,3,5},{2,4,10},{3,4,2},{3,5,6},{4,5,3}};

        int comps = n, total = 0;
        while (comps > 1) {
            Map<Integer, int[]> cheap = new HashMap<>();
            for (int[] ed : e) {
                int ru = find(ed[0]), rv = find(ed[1]);
                if (ru == rv) continue;
                if (!cheap.containsKey(ru) || ed[2] < cheap.get(ru)[2]) cheap.put(ru, ed);
                if (!cheap.containsKey(rv) || ed[2] < cheap.get(rv)[2]) cheap.put(rv, ed);
            }
            boolean mergedAny = false;
            List<Integer> roots = new ArrayList<>(cheap.keySet());
            for (int r : roots) {
                int[] ed = cheap.get(r);
                int ru = find(ed[0]), rv = find(ed[1]);
                if (ru == rv) continue;
                p[ru] = rv;
                comps--; total += ed[2];
                mergedAny = true;
                System.out.println("edge " + ed[0] + "-" + ed[1] + " w=" + ed[2]);
            }
            if (!mergedAny) break;
        }
        System.out.println("MST weight: " + total);   // 20
    }
}
