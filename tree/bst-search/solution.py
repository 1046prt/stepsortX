# sortsort · BST Search
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-search

# Binary Search Tree search: returns True/False, prints the visited path

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


def search(root, target):
    # Walk down from the root, comparing against each visited node.
    path = []
    found = False
    cur = root
    while cur is not None:
        path.append(cur.val)
        if target == cur.val:
            found = True
            break
        cur = cur.left if target < cur.val else cur.right
    verdict = "FOUND" if found else "NOT FOUND"
    print("search", target, ": path", path, "->", verdict)
    return found


if __name__ == "__main__":
    root = build([50, 30, 70, 20, 40, 60, 80])
    for t in [40, 65, 80]:
        search(root, t)
