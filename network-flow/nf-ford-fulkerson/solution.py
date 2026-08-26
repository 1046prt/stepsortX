# sortsort · Ford-Fulkerson
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-ford-fulkerson

def dfs(residual, u, t, visited, flow):
    # Find one augmenting path with DFS and return the amount pushed
    if u == t:
        return flow
    visited[u] = True
    for v in range(len(residual)):
        if not visited[v] and residual[u][v] > 0:
            pushed = dfs(residual, v, t, visited, min(flow, residual[u][v]))
            if pushed > 0:
                residual[u][v] -= pushed
                residual[v][u] += pushed
                return pushed
    return 0


def ford_fulkerson(n, capacity, source, sink):
    residual = [row[:] for row in capacity]
    max_flow = 0
    while True:
        pushed = dfs(residual, source, sink, [False] * n, float("inf"))
        if pushed == 0:
            break
        max_flow += pushed
    return max_flow


if __name__ == "__main__":
    n = 6
    capacity = [[0] * n for _ in range(n)]
    edges = [
        (0, 1, 16), (0, 2, 13),
        (1, 3, 12),
        (2, 1, 4), (2, 4, 14),
        (3, 2, 9), (3, 5, 20),
        (4, 3, 7), (4, 5, 4),
    ]
    for u, v, c in edges:
        capacity[u][v] = c
    print("Max flow:", ford_fulkerson(n, capacity, 0, 5))
