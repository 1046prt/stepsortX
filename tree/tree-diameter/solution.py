# Stepsort · Tree Diameter
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-diameter

from collections import deque


def bfs_farthest(adj, src):
    # Returns farthest node, its distance and BFS parents.
    dist = {src: 0}
    parent = {src: None}
    q = deque([src])
    far = src
    while q:
        u = q.popleft()
        for v in adj[u]:
            if v not in dist:
                dist[v] = dist[u] + 1
                parent[v] = u
                if dist[v] > dist[far]:
                    far = v
                q.append(v)
    return far, dist[far], parent


def tree_diameter(n, edges):
    adj = [[] for _ in range(n)]
    for u, v in edges:
        adj[u].append(v)
        adj[v].append(u)

    a, _, _ = bfs_farthest(adj, 0)        # pass 1: any start node
    b, length, parent = bfs_farthest(adj, a)  # pass 2: from that endpoint

    path = []                             # walk parents back from b
    cur = b
    while cur is not None:
        path.append(cur)
        cur = parent[cur]
    return length, a, b, path[::-1]


if __name__ == "__main__":
    edges = [(0, 1), (0, 2), (2, 3), (2, 4), (4, 5), (1, 6)]
    n = 7
    length, a, b, path = tree_diameter(n, edges)
    print("Diameter length:", length)
    print("Endpoints:", a, "and", b)
    print("Path:", " -> ".join(map(str, path)))
