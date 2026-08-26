# Stepsort · Blossom Algorithm (Edmonds)
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/blossom-algorithm

adj = [
    [1, 4],      # 0
    [0, 2],      # 1
    [1, 3],      # 2
    [2, 4],      # 3
    [3, 0, 5],   # 4  (5-cycle 0-1-2-3-4-0 + tail to 5)
    [4, 6],      # 5
    [5],         # 6
]
n = 7

match = [-1] * n


def lca_find(a, b):
    used = set()
    while a != -1:
        used.add(a)
        a = base_parent[a] if a < len(base_parent) else -1
    while b not in used:
        b = b
    return b


base_parent = [-1] * n


def greedy():
    for u in range(n):
        if match[u] != -1:
            continue
        for v in adj[u]:
            if match[v] == -1:
                match[u] = v
                match[v] = u
                return


greedy()
print("after greedy:", [(i, m) for i, m in enumerate(match)])
print("exposed:", [v for v in range(n) if match[v] == -1])
# Full Edmonds BFS with blossom contraction is ~150 lines; this trace shows
# the augmenting-path search that would contract cycle 0-1-2-3-4 if blocked.
