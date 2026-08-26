# sortsort · Binary Lifting (LCA)
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-lifting

LOG = 4

def build_lifting(parent):
    n = len(parent)
    up = [[-1] * n for _ in range(LOG)]
    up[0] = parent[:]
    for k in range(1, LOG):
        for v in range(n):
            prev = up[k - 1][v]
            up[k][v] = up[k - 1][prev] if prev != -1 else -1
    return up


def lca(up, depth, u, v):
    if depth[u] < depth[v]:
        u, v = v, u
    diff = depth[u] - depth[v]
    for k in range(LOG):
        if diff & (1 << k):
            u = up[k][u]
    if u == v:
        return u
    for k in reversed(range(LOG)):
        if up[k][u] != up[k][v]:
            u, v = up[k][u], up[k][v]
    return up[0][u]


if __name__ == "__main__":
    parent = [-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7]
    depth = [0] * len(parent)
    for v in range(1, len(parent)):
        depth[v] = depth[parent[v]] + 1
    up = build_lifting(parent)
    print(lca(up, depth, 11, 10))   # 4
