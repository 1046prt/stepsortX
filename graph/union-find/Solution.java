// Stepsort · Union-Find (DSU)
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/union-find

public class Main {
    static int[] parent, rank_;

    static void init(int n) {
        parent = new int[n];
        rank_ = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    static int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];   // path compression
            x = parent[x];
        }
        return x;
    }

    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (rank_[ra] < rank_[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        if (rank_[ra] == rank_[rb]) rank_[ra]++;
        return true;
    }

    public static void main(String[] args) {
        init(6);
        int[][] ops = {{0,1},{2,3},{1,3},{4,5},{3,5}};
        for (int[] op : ops)
            System.out.println("union(" + op[0] + "," + op[1] + ") -> "
                + (union(op[0], op[1]) ? "merged" : "already connected"));
    }
}
