// Stepsort · Link-Cut Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/link-cut-tree

public class Main {
    static int[] parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
    static int[] preferredChild = new int[parent.length];

    static void access(int x) {
        System.out.println("access(" + x + "):");
        int v = x;
        while (parent[v] != -1) {
            int p = parent[v];
            int old = preferredChild[p];
            if (old != -1 && old != v)
                System.out.println("  CUT " + p + "->" + old);
            preferredChild[p] = v;
            System.out.println("  LINK " + p + "->" + v);
            v = p;
        }
    }

    public static void main(String[] args) {
        java.util.Arrays.fill(preferredChild, -1);
        access(11);
        access(9);
        access(6);
    }
}
