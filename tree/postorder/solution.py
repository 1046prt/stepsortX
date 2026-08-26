# Stepsort · Postorder Traversal
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/postorder

# Postorder traversal of a BST (left, right, root):
# recursive + iterative with two stacks.

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


def postorder_recursive(node, out):
    if node is not None:
        postorder_recursive(node.left, out)   # left
        postorder_recursive(node.right, out)  # right
        out.append(node.val)                  # root


def postorder_iterative(root):
    # s1 emits nodes in reversed postorder; s2 reverses that order.
    out = []
    if root is None:
        return out
    s1, s2 = [root], []
    while s1:
        node = s1.pop()
        s2.append(node)
        if node.left is not None:
            s1.append(node.left)
        if node.right is not None:
            s1.append(node.right)
    while s2:
        out.append(s2.pop().val)
    return out


if __name__ == "__main__":
    root = build([50, 30, 70, 20, 40, 60, 80])
    rec = []
    postorder_recursive(root, rec)
    print("recursive:", rec)
    print("iterative:", postorder_iterative(root))
