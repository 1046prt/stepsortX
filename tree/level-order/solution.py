# Stepsort · Level-order Traversal
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/level-order

# Level-order traversal (BFS) printing the tree level by level

from collections import deque

class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


def insert(root, val):
    if root is None:
        return TreeNode(val)
    if val < root.val:
        root.left = insert(root.left, val)
    else:
        root.right = insert(root.right, val)
    return root


def build(values):
    root = None
    for v in values:
        root = insert(root, v)
    return root


def level_order(root):
    # BFS with a queue; each outer pass drains exactly one level.
    if root is None:
        return
    q = deque([root])
    depth = 0
    while q:
        depth += 1
        level = []
        for _ in range(len(q)):
            node = q.popleft()
            level.append(str(node.val))
            if node.left is not None:
                q.append(node.left)
            if node.right is not None:
                q.append(node.right)
        print("level", depth, ":", " ".join(level))


if __name__ == "__main__":
    root = build([50, 30, 70, 20, 40, 60, 80])
    level_order(root)
