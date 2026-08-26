// Stepsort · Red-Black Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/red-black-tree

public class Main {

    static class Node {
        int key;
        boolean red = true;               // new nodes start red
        Node left, right, parent;
        Node(int key) { this.key = key; }
    }

    static Node root;

    static void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    static void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.right = x;
        x.parent = y;
    }

    static void insert(int key) {
        Node z = new Node(key), parent = null, cur = root;
        while (cur != null) {             // ordinary BST descent
            parent = cur;
            cur = key < cur.key ? cur.left : cur.right;
        }
        z.parent = parent;
        if (parent == null) root = z;
        else if (key < parent.key) parent.left = z;
        else parent.right = z;
        fixup(z);
    }

    static void fixup(Node z) {
        while (z.parent != null && z.parent.red) {
            Node gp = z.parent.parent;
            if (z.parent == gp.left) {
                Node u = gp.right;
                if (u != null && u.red) { // red uncle: recolor only
                    z.parent.red = u.red = false;
                    gp.red = true;
                    z = gp;
                } else {
                    if (z == z.parent.right) { z = z.parent; rotateLeft(z); }
                    z.parent.red = false; // line: rotate grandparent
                    gp.red = true;
                    rotateRight(gp);
                }
            } else {                      // mirror image
                Node u = gp.left;
                if (u != null && u.red) {
                    z.parent.red = u.red = false;
                    gp.red = true;
                    z = gp;
                } else {
                    if (z == z.parent.left) { z = z.parent; rotateRight(z); }
                    z.parent.red = false;
                    gp.red = true;
                    rotateLeft(gp);
                }
            }
        }
        root.red = false;
    }

    static void inorder(Node n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.key + " ");
        inorder(n.right);
    }

    public static void main(String[] args) {
        int[] keys = {10, 20, 30, 15, 25, 5, 1, 40, 35};
        for (int k : keys) insert(k);
        System.out.print("Inorder (must be sorted): ");
        inorder(root);
        System.out.println();
        System.out.println("Root: " + root.key + (root.red ? " RED" : " BLACK"));
    }
}
