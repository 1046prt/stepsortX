// sortsort · AVL Rotations
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/avl-rotation

// AVL tree insertion with LL, RR, LR and RL rotations

public class Main {
    static class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; this.height = 1; }
    }

    static int hgt(Node n) { return n == null ? 0 : n.height; }

    static void upd(Node n) { n.height = 1 + Math.max(hgt(n.left), hgt(n.right)); }

    static int bal(Node n) { return hgt(n.left) - hgt(n.right); }

    static Node rotRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        upd(y);
        upd(x);
        return x;
    }

    static Node rotLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        upd(x);
        upd(y);
        return y;
    }

    static Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; // no duplicates
        upd(node);
        int b = bal(node);
        if (b > 1 && key < node.left.key) return rotRight(node);   // left-left
        if (b < -1 && key > node.right.key) return rotLeft(node);  // right-right
        if (b > 1 && key > node.left.key) {                        // left-right
            node.left = rotLeft(node.left);
            return rotRight(node);
        }
        if (b < -1 && key < node.right.key) {                      // right-left
            node.right = rotRight(node.right);
            return rotLeft(node);
        }
        return node;
    }

    static void preorder(Node n) {
        if (n == null) return;
        System.out.print(n.key + " ");
        preorder(n.left);
        preorder(n.right);
    }

    public static void main(String[] args) {
        Node root = null;
        for (int k = 10; k >= 1; k--) root = insert(root, k);
        System.out.print("preorder after inserting 10..1: ");
        preorder(root);
        System.out.println();
        System.out.println("root height stays about log2(10) = 4: " + hgt(root));
    }
}
