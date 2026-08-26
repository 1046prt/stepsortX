// sortsort · Konig's Min Vertex Cover
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/konig-vertex-cover

import java.util.*;

public class Main {
    static List<List<Integer>> adj;
    static int[] matchR;
    static boolean[] vis, seenL, seenR;

    static boolean tryKuhn(int u) {
        for (int v : adj.get(u)) {
            if (vis[v]) continue;
            vis[v] = true;
            if (matchR[v] == -1 || tryKuhn(matchR[v])) {
                matchR[v] = u;
                return true;
            }
        }
        return false;
    }

    static void altDfs(int u) {
        seenL[u] = true;
        for (int v : adj.get(u)) {
            if (seenR[v]) continue;
            seenR[v] = true;
            int partner = -1;
            for (int cand = 0; cand < matchR.length; cand++)
                if (matchR[cand] == v) { partner = cand; break; }
            if (partner != -1 && !seenL[partner]) altDfs(partner);
        }
    }

    public static void main(String[] args) {
        int nL = 3, nR = 3;
        adj = List.of(List.of(0, 1), List.of(0), List.of(1, 2));
        matchR = new int[nR];
        Arrays.fill(matchR, -1);
        vis = new boolean[nR];

        int matching = 0;
        for (int u = 0; u < nL; u++) {
            Arrays.fill(vis, false);
            if (tryKuhn(u)) matching++;
        }
        seenL = new boolean[nL]; seenR = new boolean[nR];
        boolean[] matchedLeft = new boolean[nL];
        for (int v = 0; v < nR; v++) if (matchR[v] != -1) matchedLeft[matchR[v]] = true;
        for (int u = 0; u < nL; u++) if (!matchedLeft[u]) altDfs(u);

        List<Integer> coverL = new ArrayList<>(), coverR = new ArrayList<>();
        for (int u = 0; u < nL; u++) if (!seenL[u]) coverL.add(u);
        for (int v = 0; v < nR; v++) if (seenR[v]) coverR.add(v);

        System.out.println("matching=" + matching + " | cover-left=" + coverL + " cover-right=" + coverR);
    }
}
