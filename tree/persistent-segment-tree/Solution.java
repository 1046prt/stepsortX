// Stepsort · Persistent Segment Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/persistent-segment-tree

import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Node {
        long sum;
        Node left, right;
        Node(long s) { sum = s; }
    }

    static Node build(int[] arr, int l, int r) {
        if (l == r) return new Node(arr[l]);
        int mid = (l + r) / 2;
        Node node = new Node(0);
        node.left = build(arr, l, mid);
        node.right = build(arr, mid + 1, r);
        node.sum = node.left.sum + node.right.sum;
        return node;
    }

    static Node update(Node prev, int l, int r, int idx, int val) {
        Node node = new Node(prev.sum);
        if (l == r) { node.sum = val; return node; }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            node.left = update(prev.left, l, mid, idx, val);
            node.right = prev.right;
        } else {
            node.right = update(prev.right, mid + 1, r, idx, val);
            node.left = prev.left;
        }
        node.sum = node.left.sum + node.right.sum;
        return node;
    }

    static long query(Node node, int l, int r, int lo, int hi) {
        if (hi < l || r < lo) return 0;
        if (lo <= l && r <= hi) return node.sum;
        int mid = (l + r) / 2;
        return query(node.left, l, mid, lo, hi) + query(node.right, mid + 1, r, lo, hi);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        List<Node> roots = new ArrayList<>();
        roots.add(build(arr, 0, 3));
        roots.add(update(roots.get(0), 0, 3, 2, 9));
        System.out.println("v0 sum[0..1] = " + query(roots.get(0), 0, 3, 0, 1));
        System.out.println("v1 sum[0..1] = " + query(roots.get(1), 0, 3, 0, 1));
    }
}
