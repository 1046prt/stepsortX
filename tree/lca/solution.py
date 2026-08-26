# Stepsort · Lowest Common Ancestor
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lca

class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


def build_bst(values):
    root = None
    for v in values:
        if root is None:
            root = TreeNode(v)
            continue
        cur = root
        while True:
            if v < cur.val:
                if cur.left:
                    cur = cur.left
                else:
                    cur.left = TreeNode(v)
                    break
            else:
                if cur.right:
                    cur = cur.right
                else:
                    cur.right = TreeNode(v)
                    break
    return root


def lca_bst(root, p, q):
    # Walk down: both smaller go left, both larger go right,
    # otherwise the current node is the split point (the LCA).
    cur = root
    while cur:
        if p < cur.val and q < cur.val:
            cur = cur.left
        elif p > cur.val and q > cur.val:
            cur = cur.right
        else:
            return cur


def lca_general(root, p, q):
    # Post-order recursion for ANY binary tree:
    # first node whose left and right searches both succeed.
    if root is None or root.val == p or root.val == q:
        return root
    left = lca_general(root.left, p, q)
    right = lca_general(root.right, p, q)
    if left and right:
        return root
    return left if left else right


if __name__ == "__main__":
    root = build_bst([20, 8, 22, 4, 12, 10, 14])
    for a, b in [(10, 14), (14, 8), (10, 22)]:
        print(
            "LCA(", a, ",", b, ") -> BST:",
            lca_bst(root, a, b).val,
            "| general:", lca_general(root, a, b).val,
        )
