# sortsort · Tree DP (Max Independent Set)
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-dp-independent-set

import sys
from functools import lru_cache

parent = [-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7]
weight = [3, 2, 1, 4, 2, 1, 3, 2, 5, 1, 2, 4]
children = {}
for v, p in enumerate(parent):
    children.setdefault(p, []).append(v)

sys.setrecursionlimit(10000)

def dfs(v):
    take_v = weight[v]
    skip_v = 0
    for c in children.get(v, []):
        tc, sc = dfs(c)
        take_v += sc
        skip_v += max(tc, sc)
    return take_v, skip_v


if __name__ == "__main__":
    take, skip = dfs(0)
    print("max independent set weight:", max(take, skip))
