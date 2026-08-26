# Stepsort · Minimum Cut Reconstruction
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-cut-reconstruction

from collections import deque

def bfs_residual(cap, source, sink, parent, n):
    visited = [False] * n
    queue = deque([source])
    visited[source] = True
    while queue:
        u = queue.popleft()
        for v in range(n):
            if not visited[v] and cap[u][v] > 0:
                visited[v] = True
                parent[v] = u
                if v == sink: return True
                queue.append(v)
    return False

def max_flow_min_cut(cap, source, sink, n):
    parent = [-1] * n
    max_f = 0
    while bfs_residual(cap, source, sink, parent, n):
        flow = float("inf")
        v = sink
        while v != source:
            flow = min(flow, cap[parent[v]][v])
            v = parent[v]
        v = sink
        while v != source:
            cap[parent[v]][v] -= flow
            cap[v][parent[v]] += flow
            v = parent[v]
        max_f += flow
        parent = [-1] * n
    visited = [False] * n
    queue = deque([source])
    visited[source] = True
    while queue:
        u = queue.popleft()
        for v in range(n):
            if not visited[v] and cap[u][v] > 0:
                visited[v] = True
                queue.append(v)
    cut_edges = []
    for u in range(n):
        if visited[u]:
            for v in range(n):
                if not visited[v] and cap[u][v] == 0:
                    cut_edges.append((u, v))
    return max_f, cut_edges

cap = [[0]*6 for _ in range(6)]
for u,v,w in [(0,1,16),(0,2,13),(1,2,10),(1,3,12),(2,1,4),(2,4,14),(3,2,9),(3,5,20),(4,3,7),(4,5,4)]:
    cap[u][v] = w
flow, cut = max_flow_min_cut(cap, 0, 5, 6)
print(f"Max flow: {flow}, Min cut edges: {cut}")
