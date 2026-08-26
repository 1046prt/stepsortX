// sortsort · Wavelet Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/wavelet-tree

import java.util.Arrays;

public class Main {
    static class WNode {
        int lo, hi;
        int[] bound = {0};
        WNode left, right;
    }

    static WNode build(int[] seq, int lo, int hi) {
        WNode node = new WNode();
        node.lo = lo;
        node.hi = hi;
        if (lo == hi || seq.length == 0) return node;
        int mid = (lo + hi) / 2;
        int[] ls = new int[seq.length];
        int[] rs = new int[seq.length];
        int[] bound = new int[seq.length + 1];
        int rl = 0, rr = 0;
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] <= mid) ls[rl++] = seq[i];
            else rs[rr++] = seq[i];
            bound[i + 1] = rl;
        }
        node.bound = bound;
        node.left = build(Arrays.copyOf(ls, rl), lo, mid);
        node.right = build(Arrays.copyOf(rs, rr), mid + 1, hi);
        return node;
    }

    static int rankOf(WNode node, int c, int i) {
        while (node.lo != node.hi) {
            int mid = (node.lo + node.hi) / 2;
            if (c <= mid) { i = node.bound[i]; node = node.left; }
            else { i -= node.bound[i]; node = node.right; }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 2};
        WNode root = build(arr, 1, 5);
        System.out.println("rank(1, 6) = " + rankOf(root, 1, 6));
        System.out.println("rank(5, 6) = " + rankOf(root, 5, 6));
    }
}
