// Stepsort · BST Insert
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-insert

// Binary Search Tree: iterative insert + inorder print demo

public class Main {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    // Iterative insert; returns the root after insertion.
    static Node insert(Node root, int val) {
        Node node = new Node(val);
        if (root == null) return node;
        Node cur = root;
        while (true) {
            if (val < cur.val) {
                if (cur.left == null) { cur.left = node; break; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = node; break; }
                cur = cur.right;
            }
        }
        return root;
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            root = insert(root, v);
            System.out.print("inserted " + v + " -> ");
            inorder(root);
            System.out.println();
        }
    }
}
