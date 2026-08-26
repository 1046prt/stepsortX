# Stepsort · B-Tree Operations
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/b-tree

class BTreeNode:
    def __init__(self, leaf):
        self.leaf = leaf
        self.keys = []          # at most 2t - 1 keys per node
        self.children = []


class BTree:
    def __init__(self, t):
        self.t = t              # minimum degree
        self.root = BTreeNode(True)

    def split_child(self, x, i):
        # Split the full child x.children[i]; median moves up into x.
        t = self.t
        y = x.children[i]
        z = BTreeNode(y.leaf)
        mid = y.keys[t - 1]
        z.keys = y.keys[t:]               # upper half goes to new sibling
        y.keys = y.keys[:t - 1]           # lower half stays in y
        if not y.leaf:
            z.children = y.children[t:]
            y.children = y.children[:t]
        x.children.insert(i + 1, z)
        x.keys.insert(i, mid)

    def insert_nonfull(self, x, k):
        if x.leaf:
            pos = len(x.keys)
            while pos > 0 and k < x.keys[pos - 1]:
                pos -= 1
            x.keys.insert(pos, k)         # keep leaf keys sorted
            return
        i = len(x.keys) - 1
        while i >= 0 and k < x.keys[i]:
            i -= 1
        i += 1
        # Preemptive split: never descend into a full child.
        if len(x.children[i].keys) == 2 * self.t - 1:
            self.split_child(x, i)
            if k > x.keys[i]:
                i += 1
        self.insert_nonfull(x.children[i], k)

    def insert(self, k):
        r = self.root
        if len(r.keys) == 2 * self.t - 1:
            s = BTreeNode(False)
            s.children.append(r)
            self.root = s
            self.split_child(s, 0)
            self.insert_nonfull(s, k)
        else:
            self.insert_nonfull(r, k)

    def traverse(self, x=None):
        if x is None:
            x = self.root
        for i in range(len(x.keys)):
            if not x.leaf:
                self.traverse(x.children[i])
            print(x.keys[i], end=" ")
        if not x.leaf:
            self.traverse(x.children[-1])


if __name__ == "__main__":
    bt = BTree(3)
    for v in [10, 20, 5, 6, 12, 30, 7, 17, 3, 25, 1, 40, 8]:
        bt.insert(v)
    print("B-Tree traversal (t = 3):")
    bt.traverse()
    print()
