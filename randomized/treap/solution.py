# sortsort · Treap
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/treap

class Node:
    __slots__ = ["key", "pri", "left", "right"]

    def __init__(self, key, pri):
        self.key = key
        self.pri = pri
        self.left = None
        self.right = None


def rotate_right(t):
    l = t.left
    t.left = l.right
    l.right = t
    return l


def rotate_left(t):
    r = t.right
    t.right = r.left
    r.left = t
    return r


def insert(t, key, pri):
    if t is None:
        return Node(key, pri)
    if key < t.key:
        t.left = insert(t.left, key, pri)
        if t.left.pri > t.pri:
            t = rotate_right(t)
    elif key > t.key:
        t.right = insert(t.right, key, pri)
        if t.right.pri > t.pri:
            t = rotate_left(t)
    return t


def inorder(t, out):
    if t is None:
        return
    inorder(t.left, out)
    out.append(t.key)
    inorder(t.right, out)


if __name__ == "__main__":
    root = None
    items = [(50, 9), (30, 14), (70, 4), (20, 16), (40, 7)]
    for key, pri in items:
        root = insert(root, key, pri)
        keys = []
        inorder(root, keys)
        print("after inserting (" + str(key) + ", " + str(pri) + "):", keys)
