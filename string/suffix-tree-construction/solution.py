# sortsort · Suffix Tree (Compressed)
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-tree-construction

class Node:
    def __init__(self):
        self.children = {}
        self.is_leaf = False


def build_suffix_tree(s):
    root = Node()
    node_count = 1
    leaf_count = 0
    for k in range(1, len(s) + 1):
        suffix = s[len(s) - k:]
        cur = root
        pos = 0
        while pos < len(suffix):
            ch = suffix[pos]
            if ch not in cur.children:
                new = Node()
                cur.children[ch] = new
                node_count += 1
            cur = cur.children[ch]
            pos += 1
        if not cur.is_leaf:
            cur.is_leaf = True
            leaf_count += 1
    return node_count, leaf_count


if __name__ == "__main__":
    nodes, leaves = build_suffix_tree("banana")
    print(f"nodes: {nodes}, leaves: {leaves}")
