// Stepsort · Binary Tree Path Sum
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-tree-path-sum

public class Main {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static boolean hasPathSum(TreeNode node, long target) {
        // True iff some root-to-leaf path sums exactly to target.
        if (node == null) return false;
        boolean leaf = node.left == null && node.right == null;
        if (leaf) return node.val == target;
        long remaining = target - node.val;
        return hasPathSum(node.left, remaining) || hasPathSum(node.right, remaining);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        System.out.println(hasPathSum(root, 22)); // 5 + 4 + 11 + 2
        System.out.println(hasPathSum(root, 30));
    }
}
