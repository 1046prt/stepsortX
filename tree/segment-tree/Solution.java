// sortsort · Segment Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/segment-tree

// Segment tree with range-sum query and point update

public class Main {
    static int n;
    static long[] tree;

    static void build(long[] values, int node, int lo, int hi) {
        if (lo == hi) {
            tree[node] = values[lo];
            return;
        }
        int mid = (lo + hi) / 2;
        build(values, 2 * node, lo, mid);
        build(values, 2 * node + 1, mid + 1, hi);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    // sum over the intersection of [l, r] with segment [lo, hi]
    static long query(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return tree[node];
        int mid = (lo + hi) / 2;
        long left = query(2 * node, lo, mid, l, r);
        long right = query(2 * node + 1, mid + 1, hi, l, r);
        return left + right;
    }

    static void update(int node, int lo, int hi, int pos, long value) {
        if (lo == hi) {
            tree[node] = value;
            return;
        }
        int mid = (lo + hi) / 2;
        if (pos <= mid) update(2 * node, lo, mid, pos, value);
        else update(2 * node + 1, mid + 1, hi, pos, value);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    public static void main(String[] args) {
        long[] arr = {2, 5, 1, 4, 9, 3};
        n = arr.length;
        tree = new long[4 * n];
        build(arr, 1, 0, n - 1);
        System.out.println("sum arr[1..3]: " + query(1, 0, n - 1, 1, 3));
        System.out.println("sum arr[0..5]: " + query(1, 0, n - 1, 0, 5));
        update(1, 0, n - 1, 2, 10);  // arr[2] = 10
        System.out.println("after setting arr[2] = 10");
        System.out.println("sum arr[1..3]: " + query(1, 0, n - 1, 1, 3));
        System.out.println("sum arr[0..5]: " + query(1, 0, n - 1, 0, 5));
    }
}
