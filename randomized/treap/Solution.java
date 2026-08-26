// sortsort · Treap
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/treap

public class Main {
    static class Node {
        int key, pri;
        Node left, right;
        Node(int key, int pri) { this.key = key; this.pri = pri; }
    }

    static Node rotateRight(Node t) {
        Node l = t.left;
        t.left = l.right;
        l.right = t;
        return l;
    }

    static Node rotateLeft(Node t) {
        Node r = t.right;
        t.right = r.left;
        r.left = t;
        return r;
    }

    static Node insert(Node t, int key, int pri) {
        if (t == null) return new Node(key, pri);
        if (key < t.key) {
            t.left = insert(t.left, key, pri);
            if (t.left.pri > t.pri) t = rotateRight(t);
        } else if (key > t.key) {
            t.right = insert(t.right, key, pri);
            if (t.right.pri > t.pri) t = rotateLeft(t);
        }
        return t;
    }

    static void inorder(Node t, StringBuilder sb) {
        if (t == null) return;
        inorder(t.left, sb);
        if (sb.length() > 0) sb.append(" ");
        sb.append(t.key);
        inorder(t.right, sb);
    }

    public static void main(String[] args) {
        Node root = null;
        int[][] items = {{50, 9}, {30, 14}, {70, 4}, {20, 16}, {40, 7}};
        for (int[] kp : items) {
            root = insert(root, kp[0], kp[1]);
            StringBuilder sb = new StringBuilder();
            inorder(root, sb);
            System.out.println("after inserting (" + kp[0] + ", " + kp[1] + "): " + sb);
        }
    }
}
