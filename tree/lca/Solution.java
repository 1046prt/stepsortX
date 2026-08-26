// Stepsort · Lowest Common Ancestor
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lca

public class Main {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode buildBST(int[] values) {
        TreeNode root = null;
        for (int v : values) {
            if (root == null) { root = new TreeNode(v); continue; }
            TreeNode cur = root;
            while (true) {
                if (v < cur.val) {
                    if (cur.left == null) { cur.left = new TreeNode(v); break; }
                    cur = cur.left;
                } else {
                    if (cur.right == null) { cur.right = new TreeNode(v); break; }
                    cur = cur.right;
                }
            }
        }
        return root;
    }

    // BST version: value comparison decides the branch to follow.
    static TreeNode lcaBST(TreeNode root, int p, int q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p < cur.val && q < cur.val) cur = cur.left;
            else if (p > cur.val && q > cur.val) cur = cur.right;
            else return cur;          // split point
        }
        return null;
    }

    // General binary tree version: post-order recursion.
    static TreeNode lcaGeneral(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) return root;
        TreeNode L = lcaGeneral(root.left, p, q);
        TreeNode R = lcaGeneral(root.right, p, q);
        if (L != null && R != null) return root;
        return L != null ? L : R;
    }

    public static void main(String[] args) {
        TreeNode root = buildBST(new int[]{20, 8, 22, 4, 12, 10, 14});
        int[][] pairs = {{10, 14}, {14, 8}, {10, 22}};
        for (int[] pr : pairs) {
            int p = pr[0], q = pr[1];
            System.out.println("LCA(" + p + ", " + q + ") -> BST: "
                + lcaBST(root, p, q).val
                + " | general: " + lcaGeneral(root, p, q).val);
        }
    }
}
