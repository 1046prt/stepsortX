# Stepsort · Red-Black Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/red-black-tree

class RBNode:
    def __init__(self, key):
        self.key = key
        self.red = True            # new nodes start red
        self.left = self.right = self.parent = None


class RedBlackTree:
    def __init__(self):
        self.root = None

    def _rotate_left(self, x):
        y = x.right
        x.right = y.left
        if y.left:
            y.left.parent = x
        y.parent = x.parent
        if x.parent is None:
            self.root = y
        elif x is x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left, x.parent = x, y

    def _rotate_right(self, x):
        y = x.left
        x.left = y.right
        if y.right:
            y.right.parent = x
        y.parent = x.parent
        if x.parent is None:
            self.root = y
        elif x is x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.right, x.parent = x, y

    def insert(self, key):
        node, parent, cur = RBNode(key), None, self.root
        while cur:                 # ordinary BST descent
            parent, cur = cur, cur.left if key < cur.key else cur.right
        node.parent = parent
        if parent is None:
            self.root = node
        elif key < parent.key:
            parent.left = node
        else:
            parent.right = node
        self._fixup(node)

    def _fixup(self, z):
        while z.parent and z.parent.red:
            gp = z.parent.parent
            if z.parent is gp.left:
                uncle = gp.right
                if uncle and uncle.red:      # red uncle: recolor only
                    z.parent.red = uncle.red = False
                    gp.red = True
                    z = gp
                else:
                    if z is z.parent.right:  # triangle: extra rotation
                        z = z.parent
                        self._rotate_left(z)
                    z.parent.red = False     # line: rotate grandparent
                    gp.red = True
                    self._rotate_right(gp)
            else:                            # mirror image
                uncle = gp.left
                if uncle and uncle.red:
                    z.parent.red = uncle.red = False
                    gp.red = True
                    z = gp
                else:
                    if z is z.parent.left:
                        z = z.parent
                        self._rotate_right(z)
                    z.parent.red = False
                    gp.red = True
                    self._rotate_left(gp)
        self.root.red = False

    def inorder(self):
        out = []

        def walk(n):
            if n:
                walk(n.left)
                out.append(n.key)
                walk(n.right)

        walk(self.root)
        return out


if __name__ == "__main__":
    rbt = RedBlackTree()
    for k in [10, 20, 30, 15, 25, 5, 1, 40, 35]:
        rbt.insert(k)
    print("Inorder (must be sorted):", rbt.inorder())
    print("Root:", rbt.root.key, "(RED)" if rbt.root.red else "(BLACK)")
