# sortsort · Binary Tree Level Order
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-tree-level-order

from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def level_order(root):
    # BFS: process the queue one level at a time.
    if not root:
        return []
    levels = []
    queue = deque([root])
    while queue:
        size = len(queue)
        level = []
        for _ in range(size):
            node = queue.popleft()
            level.append(node.val)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        levels.append(level)
    return levels


if __name__ == "__main__":
    root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
    for level in level_order(root):
        print(level)
