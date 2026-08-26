// sortsort · BST Search
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-search

// Binary Search Tree search: returns true/false, prints the visited path

public class Main {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // Walk down from the root, comparing against each visited node.
    static boolean search(Node root, int target) {
        StringBuilder path = new StringBuilder();
        boolean found = false;
        Node cur = root;
        while (cur != null) {
            if (path.length() > 0) path.append(" ");
            path.append(cur.val);
            if (target == cur.val) { found = true; break; }
            cur = (target < cur.val) ? cur.left : cur.right;
        }
        String verdict = found ? "FOUND" : "NOT FOUND";
        System.out.println("search " + target + " : path " + path + " -> " + verdict);
        return found;
    }

    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) root = insert(root, v);
        int[] queries = {40, 65, 80};
        for (int t : queries) search(root, t);
    }
}
