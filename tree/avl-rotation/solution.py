# sortsort · AVL Rotations
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/avl-rotation

# AVL tree insertion with LL, RR, LR and RL rotations

class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None
        self.height = 1


def height(node):
    return node.height if node else 0


def update_height(node):
    node.height = 1 + max(height(node.left), height(node.right))


def balance_factor(node):
    return height(node.left) - height(node.right)


def rotate_right(y):
    x = y.left
    t2 = x.right
    x.right = y
    y.left = t2
    update_height(y)
    update_height(x)
    return x


def rotate_left(x):
    y = x.right
    t2 = y.left
    y.left = x
    x.right = t2
    update_height(x)
    update_height(y)
    return y


def insert(node, key):
    if not node:
        return Node(key)
    if key < node.key:
        node.left = insert(node.left, key)
    elif key > node.key:
        node.right = insert(node.right, key)
    else:
        return node  # no duplicates
    update_height(node)
    bf = balance_factor(node)
    if bf > 1 and key < node.left.key:      # left-left case
        return rotate_right(node)
    if bf < -1 and key > node.right.key:    # right-right case
        return rotate_left(node)
    if bf > 1 and key > node.left.key:      # left-right case
        node.left = rotate_left(node.left)
        return rotate_right(node)
    if bf < -1 and key < node.right.key:    # right-left case
        node.right = rotate_right(node.right)
        return rotate_left(node)
    return node


def preorder(node, out):
    if node:
        out.append(str(node.key))
        preorder(node.left, out)
        preorder(node.right, out)


if __name__ == "__main__":
    root = None
    for k in range(10, 0, -1):  # insert 10 down to 1
        root = insert(root, k)
    out = []
    preorder(root, out)
    print("preorder after inserting 10..1:", " ".join(out))
