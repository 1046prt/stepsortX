// Stepsort · Li Chao Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/li-chao-tree

public class Main {
    static final int LO = -5, HI = 5;

    static class Node {
        long m, b;
        Node left, right;
    }

    static long f(long m, long b, long x) { return m * x + b; }

    static Node insert(Node node, int l, int r, long m, long b) {
        if (node == null) {
            Node fresh = new Node();
            fresh.m = m;
            fresh.b = b;
            return fresh;
        }
        int mid = (l + r) / 2;
        boolean leftWin = f(m, b, l) < f(node.m, node.b, l);
        boolean middleWin = f(m, b, mid) < f(node.m, node.b, mid);
        if (middleWin) {
            long tm = node.m; node.m = m; m = tm;
            long tb = node.b; node.b = b; b = tb;
        }
        if (l == r) return node;
        if (leftWin != middleWin) node.left = insert(node.left, l, mid, m, b);
        else node.right = insert(node.right, mid + 1, r, m, b);
        return node;
    }

    static long query(Node node, int l, int r, int x) {
        long best = f(node.m, node.b, x);
        while (l < r) {
            int mid = (l + r) / 2;
            if (x <= mid) { node = node.left; r = mid; }
            else { node = node.right; l = mid + 1; }
            if (node == null) break;
            best = Math.min(best, f(node.m, node.b, x));
        }
        return best;
    }

    public static void main(String[] args) {
        Node root = null;
        root = insert(root, LO, HI, 1, 0);
        root = insert(root, LO, HI, -1, 6);
        root = insert(root, LO, HI, 0, -2);
        System.out.println("min at x = -3: " + query(root, LO, HI, -3));
        System.out.println("min at x = 4: " + query(root, LO, HI, 4));
    }
}
