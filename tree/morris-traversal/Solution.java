// Stepsort · Morris Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/morris-traversal

public class Main {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static void morrisInorder(Node root) {
        Node cur = root;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.val + " ");   // no left subtree: visit
                cur = cur.right;
            } else {
                Node pred = cur.left;
                while (pred.right != null && pred.right != cur) pred = pred.right;
                if (pred.right == null) {
                    pred.right = cur;              // create thread, go left
                    cur = cur.left;
                } else {
                    pred.right = null;             // remove thread: restore
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                }
            }
        }
    }

    static Node insert(Node root, int v) {
        if (root == null) return new Node(v);
        if (v < root.val) root.left = insert(root.left, v);
        else root.right = insert(root.right, v);
        return root;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) root = insert(root, v);
        System.out.println("Morris inorder:");
        morrisInorder(root);
        System.out.println();
        morrisInorder(root);   // second pass proves the tree was restored
        System.out.println();
    }
}
