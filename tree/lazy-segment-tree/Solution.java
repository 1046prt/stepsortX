// sortsort · Lazy Propagation Segment Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lazy-segment-tree

public class Main {
    static long[] sum, lazy;
    static int n;

    static void build(int node, int l, int r, long[] a) {
        if (l == r) { sum[node] = a[l]; return; }
        int m = (l + r) / 2;
        build(2*node, l, m, a);
        build(2*node+1, m+1, r, a);
        sum[node] = sum[2*node] + sum[2*node+1];
    }
    static void apply(int node, int l, int r, long v) {
        sum[node] += v * (r - l + 1L);
        lazy[node] += v;
    }
    static void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int m = (l + r) / 2;
            apply(2*node, l, m, lazy[node]);
            apply(2*node+1, m+1, r, lazy[node]);
            lazy[node] = 0;
        }
    }
    static void update(int node, int l, int r, int ql, int qr, long v) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) { apply(node, l, r, v); return; }
        push(node, l, r);
        int m = (l + r) / 2;
        update(2*node, l, m, ql, qr, v);
        update(2*node+1, m+1, r, ql, qr, v);
        sum[node] = sum[2*node] + sum[2*node+1];
    }
    static long query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        push(node, l, r);
        if (ql <= l && r <= qr) return sum[node];
        int m = (l + r) / 2;
        return query(2*node, l, m, ql, qr) + query(2*node+1, m+1, r, ql, qr);
    }

    public static void main(String[] args) {
        long[] a = {1, 3, 5, 7, 9, 11};
        n = a.length;
        sum = new long[4*n]; lazy = new long[4*n];
        build(1, 0, n-1, a);
        update(1, 0, n-1, 1, 3, 5);
        System.out.println(query(1, 0, n-1, 1, 3));   // 36
    }
}
