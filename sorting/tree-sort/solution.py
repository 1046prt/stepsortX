# sortsort · Tree Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-sort

class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


def insert(root, key):
    if root is None:
        return Node(key)
    if key < root.key:
        root.left = insert(root.left, key)
    else:
        root.right = insert(root.right, key)
    return root


def inorder(node, out):
    if node is None:
        return
    inorder(node.left, out)
    out.append(node.key)
    inorder(node.right, out)


def tree_sort(arr):
    # BST insert every element, then an inorder walk yields sorted order.
    root = None
    for key in arr:
        root = insert(root, key)
    result = []
    inorder(root, result)
    return result


if __name__ == "__main__":
    data = [7, 2, 9, 1, 5, 5, 3]
    print("array:", data)
    print("sorted:", tree_sort(data))
