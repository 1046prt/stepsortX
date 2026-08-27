// Stepsort · Weighted Union-Find
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/union-find-weighted

public class UnionFindWeighted {
    int[] parent, rank;
    int components;

    UnionFindWeighted(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return false;
        if (rank[rx] < rank[ry]) { int t = rx; rx = ry; ry = t; }
        parent[ry] = rx;
        if (rank[rx] == rank[ry]) rank[rx]++;
        components--;
        return true;
    }

    boolean connected(int x, int y) { return find(x) == find(y); }

    public static void main(String[] args) {
        UnionFindWeighted uf = new UnionFindWeighted(6);
        uf.union(0, 1); uf.union(2, 3); uf.union(1, 3);
        System.out.println("0 and 3 connected? " + uf.connected(0, 3)); // true
        System.out.println("0 and 4 connected? " + uf.connected(0, 4)); // false
    }
}
