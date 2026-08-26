# Stepsort · Eulerian Path/Circuit
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/eulerian-path

def find_eulerian_path(n, edges):
    # Adjacency entries are (neighbor, edge_id) so parallel edges stay distinct
    adj = [[] for _ in range(n)]
    degree = [0] * n
    for i, (u, v) in enumerate(edges):
        adj[u].append((v, i))
        adj[v].append((u, i))
        degree[u] += 1
        degree[v] += 1
    odd_vertices = [v for v in range(n) if degree[v] % 2 == 1]
    if len(odd_vertices) not in (0, 2):
        return None  # an Eulerian path needs exactly 0 or 2 odd-degree vertices
    # Start at an odd vertex if one exists, otherwise at any edge endpoint
    start = odd_vertices[0] if odd_vertices else edges[0][0]
    used = [False] * len(edges)
    stack = [start]
    path = []
    while stack:
        v = stack[-1]
        # Lazily discard already-used edges at this vertex
        while adj[v] and used[adj[v][-1][1]]:
            adj[v].pop()
        if adj[v]:
            u, edge_id = adj[v].pop()
            used[edge_id] = True
            stack.append(u)
        else:
            path.append(stack.pop())  # backtracking records the route in reverse
    if len(path) != len(edges) + 1:
        return None  # edges were disconnected
    return path[::-1]


if __name__ == "__main__":
    # Undirected multigraph where every degree is even, so a circuit exists
    edges = [(0, 1), (1, 2), (2, 0), (0, 3), (3, 4), (4, 0)]
    path = find_eulerian_path(5, edges)
    print("Eulerian path:", path if path else "none")
