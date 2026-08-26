// Stepsort · BST Delete
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-delete

// BST deletion: leaf, one-child and two-children cases.
// Two-children case swaps in the inorder successor (leftmost of right subtree).

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

    static Node minValueNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    static Node deleteNode(Node root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Found the node to remove.
            if (root.left == null) return root.right;   // leaf or right child only
            if (root.right == null) return root.left;   // left child only
            Node succ = minValueNode(root.right);
            root.val = succ.val;                        // copy value up...
            root.right = deleteNode(root.right, succ.val); // ...delete it below
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
        for (int v : values) root = insert(root, v);
        int[] keys = {20, 30, 70};  // leaf, one child, two children
        for (int key : keys) {
            root = deleteNode(root, key);
            System.out.print("deleted " + key + " -> ");
            inorder(root);
            System.out.println();
        }
    }
}
