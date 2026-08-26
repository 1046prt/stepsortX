// Stepsort · Inorder Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/inorder

// Inorder traversal of a BST: recursive + iterative (explicit stack)

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

    static void inorderRecursive(Node node, List<Integer> out) {
        if (node == null) return;
        inorderRecursive(node.left, out);    // left
        out.add(node.val);                   // root
        inorderRecursive(node.right, out);   // right
    }

    static List<Integer> inorderIterative(Node root) {
        List<Integer> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {   // slide left, saving nodes on the stack
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();      // visit the node
            out.add(cur.val);
            cur = cur.right;        // continue with the right subtree
        }
        return out;
    }

    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) root = insert(root, v);
        List<Integer> rec = new ArrayList<>();
        inorderRecursive(root, rec);
        System.out.println("recursive: " + rec);
        System.out.println("iterative: " + inorderIterative(root));
    }
}
