# Stepsort · Li Chao Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/li-chao-tree

LO, HI = -5, 5


class Node:
    __slots__ = ["m", "b", "left", "right"]

    def __init__(self, m, b):
        self.m = m
        self.b = b
        self.left = None
        self.right = None


def f(m, b, x):
    return m * x + b


def insert(node, l, r, m, b):
    if node is None:
        return Node(m, b)
    mid = (l + r) // 2
    left_win = f(m, b, l) < f(node.m, node.b, l)
    middle_win = f(m, b, mid) < f(node.m, node.b, mid)
    if middle_win:
        node.m, m = m, node.m
        node.b, b = b, node.b
    if l == r:
        return node
    if left_win != middle_win:
        node.left = insert(node.left, l, mid, m, b)
    else:
        node.right = insert(node.right, mid + 1, r, m, b)
    return node


def query(node, l, r, x):
    best = f(node.m, node.b, x)
    while l < r:
        mid = (l + r) // 2
        if x <= mid:
            node, r = node.left, mid
        else:
            node, l = node.right, mid + 1
        if node is None:
            break
        best = min(best, f(node.m, node.b, x))
    return best


if __name__ == "__main__":
    root = None
    root = insert(root, LO, HI, 1, 0)
    root = insert(root, LO, HI, -1, 6)
    root = insert(root, LO, HI, 0, -2)
    print("min at x = -3:", query(root, LO, HI, -3))   # expect -3
    print("min at x = 4:", query(root, LO, HI, 4))     # expect -2
