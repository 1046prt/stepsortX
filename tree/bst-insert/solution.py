# sortsort · BST Insert
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-insert

# Binary Search Tree: iterative insert + inorder print demo

class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


def insert(root, val):
    # Iterative insert; returns the root after insertion.
    new_node = TreeNode(val)
    if root is None:
        return new_node
    cur = root
    while True:
        if val < cur.val:
            if cur.left is None:
                cur.left = new_node
                break
            cur = cur.left
        else:
            if cur.right is None:
                cur.right = new_node
                break
            cur = cur.right
    return root


def inorder(node):
    if node is None:
        return
    inorder(node.left)
    print(node.val, end=" ")
    inorder(node.right)


if __name__ == "__main__":
    root = None
    for v in [50, 30, 70, 20, 40, 60, 80]:
        root = insert(root, v)
        print("inserted", v, "->", end=" ")
        inorder(root)
        print()
