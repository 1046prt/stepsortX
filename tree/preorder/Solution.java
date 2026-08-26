// Stepsort · Preorder Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/preorder

// Preorder traversal of a BST (root, left, right): recursive + iterative

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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

    static void preorderRecursive(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.val);                   // root
        preorderRecursive(node.left, out);   // left
        preorderRecursive(node.right, out);  // right
    }

    static List<Integer> preorderIterative(Node root) {
        // Pop, visit, then push right before left so the left side pops first.
        List<Integer> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            out.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return out;
    }

    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) root = insert(root, v);
        List<Integer> rec = new ArrayList<>();
        preorderRecursive(root, rec);
        System.out.println("recursive: " + rec);
        System.out.println("iterative: " + preorderIterative(root));
    }
}
