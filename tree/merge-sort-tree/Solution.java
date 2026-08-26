// Stepsort · Merge Sort Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-sort-tree

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] arr = {5, 2, 6, 1, 3, 4, 7};
    static List<List<Integer>> tree = new ArrayList<>();

    static void build(int node, int l, int r) {
        List<Integer> cur = tree.get(node);
        if (l == r) { cur.add(arr[l]); return; }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        List<Integer> a = tree.get(2 * node), b = tree.get(2 * node + 1);
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) <= b.get(j)) merged.add(a.get(i++));
            else merged.add(b.get(j++));
        }
        while (i < a.size()) merged.add(a.get(i++));
        while (j < b.size()) merged.add(b.get(j++));
        cur.addAll(merged);
    }

    static int countLE(List<Integer> sorted, int x) {
        int lo = 0, hi = sorted.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sorted.get(mid) <= x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    static int query(int node, int l, int r, int lo, int hi, int x) {
        if (hi < l || r < lo) return 0;
        if (lo <= l && r <= hi) return countLE(tree.get(node), x);
        int mid = (l + r) / 2;
        return query(2 * node, l, mid, lo, hi, x) +
               query(2 * node + 1, mid + 1, r, lo, hi, x);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4 * arr.length; i++) tree.add(new ArrayList<>());
        build(1, 0, arr.length - 1);
        System.out.println("count of values <= 4 in arr[1..5]: "
                + query(1, 0, arr.length - 1, 1, 5, 4));
    }
}
