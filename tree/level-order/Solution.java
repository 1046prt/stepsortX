// sortsort · Level-order Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/level-order

// Level-order traversal (BFS) printing the tree level by level

import java.util.ArrayDeque;
import java.util.Queue;

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

    static void levelOrder(Node root) {
        // BFS with a queue; each outer pass drains exactly one level.
        if (root == null) return;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int depth = 0;
        while (!q.isEmpty()) {
            ++depth;
            int n = q.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                Node node = q.poll();
                if (sb.length() > 0) sb.append(" ");
                sb.append(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            System.out.println("level " + depth + ": " + sb);
        }
    }

    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) root = insert(root, v);
        levelOrder(root);
    }
}
