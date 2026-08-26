# Stepsort · Centroid Decomposition
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/centroid-decomposition

edges = [[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6],
         [3, 7], [3, 8], [4, 9], [5, 10], [7, 11]]
n = 12
adj = [[] for _ in range(n)]
for a, b in edges:
    adj[a].append(b)
    adj[b].append(a)


def find_centroid(alive):
    root = alive.index(True)
    par = [-1] * n
    visited = [False] * n
    visited[root] = True
    preorder = [root]
    stack = [root]
    while stack:
        v = stack.pop()
        for u in adj[v]:
            if alive[u] and not visited[u]:
                visited[u] = True
                par[u] = v
                preorder.append(u)
                stack.append(u)
    size = dict.fromkeys(preorder, 1)
    for v in reversed(preorder):
        if par[v] != -1:
            size[par[v]] += size[v]
    total = size[root]
    for v in preorder:
        worst = total - size[v]
        for u in adj[v]:
            if alive[u] and u != par[v] and visited[u]:
                worst = max(worst, size[u])
        if worst <= total // 2:
            return v
    return -1


if __name__ == "__main__":
    alive = [True] * n
    removal_order = []
    while any(alive):
        c = find_centroid(alive)
        removal_order.append(c)
        alive[c] = False
    print("centroid removal order:", removal_order)
