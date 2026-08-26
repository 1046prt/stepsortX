# sortsort · Morris Traversal
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/morris-traversal

class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


def morris_inorder(root):
    # O(1) extra space: threads from each predecessor to the node
    # replace the recursion stack; threads are removed on the way back.
    out = []
    cur = root
    while cur:
        if cur.left is None:
            out.append(cur.val)       # no left subtree: visit and go right
            cur = cur.right
        else:
            pred = cur.left
            while pred.right and pred.right is not cur:
                pred = pred.right     # rightmost node of left subtree
            if pred.right is None:
                pred.right = cur      # create thread, descend left
                cur = cur.left
            else:
                pred.right = None     # thread seen again: restore tree
                out.append(cur.val)
                cur = cur.right
    return out


def build_bst(values):
    root = None
    for v in values:
        if root is None:
            root = Node(v)
            continue
        cur = root
        while True:
            if v < cur.val:
                if cur.left:
                    cur = cur.left
                else:
                    cur.left = Node(v)
                    break
            else:
                if cur.right:
                    cur = cur.right
                else:
                    cur.right = Node(v)
                    break
    return root


if __name__ == "__main__":
    root = build_bst([50, 30, 70, 20, 40, 60, 80])
    print("Morris inorder:", morris_inorder(root))
    # Tree structure was restored, so a second run matches.
    print("Second pass:   ", morris_inorder(root))
