// Stepsort · Fenwick Tree (BIT)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fenwick-tree

// Fenwick (Binary Indexed) Tree: prefix sums with point add

public class Main {
    static int n;
    static long[] tree;

    static void add(int i, long delta) {
        // adds delta at 1-based index i, climbing to the next responsible cell
        while (i <= n) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    static long prefixSum(int i) {
        // sum of elements at indices 1..i, peeling off lowest set bits
        long total = 0;
        while (i > 0) {
            total += tree[i];
            i -= i & (-i);
        }
        return total;
    }

    static long rangeSum(int l, int r) {
        // inclusive sum over indices l..r (1-based)
        return prefixSum(r) - prefixSum(l - 1);
    }

    public static void main(String[] args) {
        long[] arr = {3, 2, -1, 6, 5, 4};
        n = arr.length;
        tree = new long[n + 1];
        for (int i = 0; i < n; i++) add(i + 1, arr[i]);

        System.out.println("prefix sum to 3: " + prefixSum(3));
        System.out.println("range sum 2..5: " + rangeSum(2, 5));
        add(4, 7);  // arr[3] += 7
        System.out.println("after adding 7 at index 4");
        System.out.println("prefix sum to 3: " + prefixSum(3));
        System.out.println("prefix sum to 6: " + prefixSum(6));
        System.out.println("range sum 2..5: " + rangeSum(2, 5));
    }
}
