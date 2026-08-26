// Stepsort · Postorder Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/postorder

// Postorder traversal of a BST (left, right, root):
// recursive + iterative with two stacks.

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    static void postorderRecursive(Node node, List<Integer> out) {
        if (node == null) return;
        postorderRecursive(node.left, out);   // left
        postorderRecursive(node.right, out);  // right
        out.add(node.val);                    // root
    }

    static List<Integer> postorderIterative(Node root) {
        // s1 emits nodes in reversed postorder; s2 reverses that order.
        List<Integer> out = new ArrayList<>();
        if (root == null) return out;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            Node node = s1.pop();
            s2.push(node);
            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }
        while (!s2.isEmpty()) {
            out.add(s2.pop().val);
        }
        return out;
    }

    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) root = insert(root, v);
        List<Integer> rec = new ArrayList<>();
        postorderRecursive(root, rec);
        System.out.println("recursive: " + rec);
        System.out.println("iterative: " + postorderIterative(root));
    }
}
