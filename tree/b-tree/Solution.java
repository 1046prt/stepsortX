// Stepsort · B-Tree Operations
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/b-tree

import java.util.*;

public class Main {

    static class BTreeNode {
        List<Integer> keys = new ArrayList<>();
        List<BTreeNode> children = new ArrayList<>();
        boolean leaf;
        BTreeNode(boolean leaf) { this.leaf = leaf; }
    }

    static final int T = 3;               // minimum degree
    static BTreeNode root = new BTreeNode(true);

    static void splitChild(BTreeNode x, int i) {
        BTreeNode y = x.children.get(i);
        BTreeNode z = new BTreeNode(y.leaf);
        int mid = y.keys.get(T - 1);
        z.keys.addAll(y.keys.subList(T, y.keys.size()));
        y.keys.subList(T - 1, y.keys.size()).clear();
        if (!y.leaf) {
            z.children.addAll(y.children.subList(T, y.children.size()));
            y.children.subList(T, y.children.size()).clear();
        }
        x.children.add(i + 1, z);
        x.keys.add(i, mid);
    }

    static void insertNonFull(BTreeNode x, int k) {
        if (x.leaf) {
            int pos = x.keys.size();
            while (pos > 0 && k < x.keys.get(pos - 1)) pos--;
            x.keys.add(pos, k);           // keep leaf keys sorted
            return;
        }
        int i = x.keys.size() - 1;
        while (i >= 0 && k < x.keys.get(i)) i--;
        i++;
        // Preemptive split: never descend into a full child.
        if (x.children.get(i).keys.size() == 2 * T - 1) {
            splitChild(x, i);
            if (k > x.keys.get(i)) i++;
        }
        insertNonFull(x.children.get(i), k);
    }

    static void insert(int k) {
        if (root.keys.size() == 2 * T - 1) {
            BTreeNode s = new BTreeNode(false);
            s.children.add(root);
            root = s;
            splitChild(s, 0);
            insertNonFull(s, k);
        } else {
            insertNonFull(root, k);
        }
    }

    static void traverse(BTreeNode x) {
        for (int i = 0; i < x.keys.size(); i++) {
            if (!x.leaf) traverse(x.children.get(i));
            System.out.print(x.keys.get(i) + " ");
        }
        if (!x.leaf) traverse(x.children.get(x.children.size() - 1));
    }

    public static void main(String[] args) {
        int[] vals = {10, 20, 5, 6, 12, 30, 7, 17, 3, 25, 1, 40, 8};
        for (int v : vals) insert(v);
        System.out.println("B-Tree traversal (t = 3):");
        traverse(root);
        System.out.println();
    }
}
