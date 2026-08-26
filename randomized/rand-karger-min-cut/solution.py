# Stepsort · Karger's Min Cut
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-karger-min-cut

import random


def find(parent, x):
    # Union-find lookup with path halving.
    while parent[x] != x:
        parent[x] = parent[parent[x]]
        x = parent[x]
    return x


def min_cut_once(vertices, edges, rng=random):
    # One contraction trial on a copy; assumes a connected graph.
    parent = {v: v for v in vertices}
    work = list(edges)
    components = len(vertices)
    while components > 2:
        u, v = work.pop(rng.randrange(len(work)))
        root_u, root_v = find(parent, u), find(parent, v)
        if root_u == root_v:
            continue  # self-loop: already contracted
        parent[root_v] = root_u
        components -= 1
    return sum(1 for u, v in work if find(parent, u) != find(parent, v))


def karger_min_cut(vertices, edges, trials=200):
    best = len(edges)
    for _ in range(trials):
        best = min(best, min_cut_once(vertices, edges))
    return best


if __name__ == "__main__":
    random.seed(42)
    vertices = ["a", "b", "c", "d"]
    edges = [("a", "b"), ("a", "c"), ("b", "c"), ("b", "d"), ("c", "d")]
    print("edges:", edges)
    print("minimum cut found over trials:", karger_min_cut(vertices, edges))
