# sortsort · Link-Cut Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/link-cut-tree

parent = [-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7]
preferred_child = [-1] * 12


def access(x):
    print("access(" + str(x) + "):")
    v = x
    while parent[v] != -1:
        p = parent[v]
        old = preferred_child[p]
        if old != -1 and old != v:
            print("  CUT " + str(p) + "->" + str(old))
        preferred_child[p] = v
        print("  LINK " + str(p) + "->" + str(v))
        v = p


if __name__ == "__main__":
    access(11)
    access(9)
    access(6)
