# sortsort · Preorder Traversal
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/preorder

# Preorder traversal of a BST (root, left, right): recursive + iterative

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


def preorder_recursive(node, out):
    if node is not None:
        out.append(node.val)                 # root
        preorder_recursive(node.left, out)   # left
        preorder_recursive(node.right, out)  # right


def preorder_iterative(root):
    # Pop, visit, then push right before left so the left side pops first.
    out, stack = [], []
    if root is not None:
        stack.append(root)
    while stack:
        node = stack.pop()
        out.append(node.val)
        if node.right is not None:
            stack.append(node.right)
        if node.left is not None:
            stack.append(node.left)
    return out


if __name__ == "__main__":
    root = build([50, 30, 70, 20, 40, 60, 80])
    rec = []
    preorder_recursive(root, rec)
    print("recursive:", rec)
    print("iterative:", preorder_iterative(root))
