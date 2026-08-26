# sortsort · Inorder Traversal
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/inorder

# Inorder traversal of a BST: recursive + iterative (explicit stack)

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


def inorder_recursive(node, out):
    if node is not None:
        inorder_recursive(node.left, out)    # left
        out.append(node.val)                 # root
        inorder_recursive(node.right, out)   # right


def inorder_iterative(root):
    out, stack = [], []
    cur = root
    while cur is not None or stack:
        while cur is not None:   # slide left, saving nodes on the stack
            stack.append(cur)
            cur = cur.left
        cur = stack.pop()        # visit the node
        out.append(cur.val)
        cur = cur.right          # continue with the right subtree
    return out


if __name__ == "__main__":
    root = build([50, 30, 70, 20, 40, 60, 80])
    rec = []
    inorder_recursive(root, rec)
    print("recursive:", rec)
    print("iterative:", inorder_iterative(root))
