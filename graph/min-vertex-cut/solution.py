# sortsort · Minimum Vertex Cut
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-vertex-cut

def min_vertex_cut(n, edges, s, t):
    N = 2 * n
    cap = [[0] * N for _ in range(N)]
    for u, v in edges:
        cap[u][v] = float("inf")
    for i in range(n):
        cap[i][i + n] = 1
    cap[s + n][s] = float("inf")
    cap[t][t + n] = float("inf")
    from collections import deque
    flow = 0
    while True:
        parent = [-1] * N
        q = deque([s + n])
        parent[s + n] = s + n
        while q:
            u = q.popleft()
            for v in range(N):
                if parent[v] == -1 and cap[u][v] > 0:
                    parent[v] = u
                    if v == t + n: break
                    q.append(v)
        if parent[t + n] == -1: break
        f = float("inf")
        v = t + n
        while v != s + n:
            f = min(f, cap[parent[v]][v])
            v = parent[v]
        v = t + n
        while v != s + n:
            cap[parent[v]][v] -= f
            cap[v][parent[v]] += f
            v = parent[v]
        flow += f
    return flow

edges = [(0,1),(0,2),(1,3),(2,3),(3,4),(3,5)]
print("Min vertex cut:", min_vertex_cut(6, edges, 0, 5))
