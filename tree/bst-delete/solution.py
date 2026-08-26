# sortsort · BST Delete
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-delete

# BST deletion: leaf, one-child and two-children cases.
# Two-children case swaps in the inorder successor (leftmost of right subtree).

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


def min_value_node(node):
    while node.left is not None:
        node = node.left
    return node


def delete(root, key):
    if root is None:
        return None
    if key < root.val:
        root.left = delete(root.left, key)
    elif key > root.val:
        root.right = delete(root.right, key)
    else:
        # Found the node to remove.
        if root.left is None:   # leaf or only a right child
            return root.right
        if root.right is None:  # only a left child
            return root.left
        succ = min_value_node(root.right)
        root.val = succ.val     # copy successor value up...
        root.right = delete(root.right, succ.val)  # ...then delete it below
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
    for key in [20, 30, 70]:  # leaf, one child, two children
        root = delete(root, key)
        print("deleted", key, "->", end=" ")
        inorder(root)
        print()
