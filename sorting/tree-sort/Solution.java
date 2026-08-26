// sortsort · Tree Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-sort

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node insert(Node root, int key) {
        if (root == null) return new Node(key);
        if (key < root.key) root.left = insert(root.left, key);
        else root.right = insert(root.right, key);
        return root;
    }

    static void inorder(Node node, List<Integer> out) {
        if (node == null) return;
        inorder(node.left, out);
        out.add(node.key);
        inorder(node.right, out);
    }

    // BST insert every element, then an inorder walk yields sorted order.
    static List<Integer> treeSort(int[] arr) {
        Node root = null;
        for (int key : arr) root = insert(root, key);
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);
        return sorted;
    }

    public static void main(String[] args) {
        int[] data = {7, 2, 9, 1, 5, 5, 3};
        System.out.println("sorted: " + treeSort(data));
    }
}
