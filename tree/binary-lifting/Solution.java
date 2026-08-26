// Stepsort · Binary Lifting (LCA)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-lifting

public class Main {
    static final int LOG = 4;

    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
        int n = parent.length;
        int[][] up = new int[LOG][n];
        up[0] = parent.clone();
        int[] depth = new int[n];
        for (int v = 1; v < n; v++) depth[v] = depth[parent[v]] + 1;
        for (int k = 1; k < LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k - 1][v] != -1 ? up[k - 1][up[k - 1][v]] : -1;

        int u = 11, v = 10;
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int k = 0; k < LOG; k++)
            if ((diff & (1 << k)) != 0) u = up[k][u];
        if (u != v)
            for (int k = LOG - 1; k >= 0; k--)
                if (up[k][u] != up[k][v]) { u = up[k][u]; v = up[k][v]; }
        System.out.println("LCA = " + parent[u]);   // 4
    }
}
