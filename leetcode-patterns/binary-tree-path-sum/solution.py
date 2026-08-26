# Stepsort · Binary Tree Path Sum
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-tree-path-sum

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def has_path_sum(root, target):
    # True iff some root-to-leaf path sums exactly to target.
    if root is None:
        return False
    if root.left is None and root.right is None:
        return root.val == target
    remaining = target - root.val
    return has_path_sum(root.left, remaining) or has_path_sum(root.right, remaining)


def build_sample():
    root = TreeNode(5)
    root.left = TreeNode(4, TreeNode(11, TreeNode(7), TreeNode(2)))
    root.right = TreeNode(8, TreeNode(13), TreeNode(4, None, TreeNode(1)))
    return root


if __name__ == "__main__":
    root = build_sample()
    print(has_path_sum(root, 22))  # 5 + 4 + 11 + 2
    print(has_path_sum(root, 30))
