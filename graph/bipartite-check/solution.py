# sortsort · Bipartite Check
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bipartite-check

from collections import deque


def bipartite_sets(n, edges):
    adj = [[] for _ in range(n)]
    for u, v in edges:
        adj[u].append(v)
        adj[v].append(u)
    color = [-1] * n
    for start in range(n):
        if color[start] != -1:
            continue
        color[start] = 0
        queue = deque([start])
        while queue:
            u = queue.popleft()
            for v in adj[u]:
                if color[v] == -1:
                    color[v] = color[u] ^ 1  # opposite color of the neighbor
                    queue.append(v)
                elif color[v] == color[u]:
                    return None  # odd cycle makes 2-coloring impossible
    set_a = [v for v in range(n) if color[v] == 0]
    set_b = [v for v in range(n) if color[v] == 1]
    return set_a, set_b


if __name__ == "__main__":
    edges = [(0, 1), (0, 3), (1, 2), (2, 3), (2, 4)]
    result = bipartite_sets(5, edges)
    if result is None:
        print("Graph is NOT bipartite")
    else:
        set_a, set_b = result
        print("Graph is bipartite")
        print("Set A:", set_a)
        print("Set B:", set_b)
