# sortsort · Biconnected Components
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/biconnected-components

def biconnected(n, edges):
    adj = [[] for _ in range(n)]
    for idx, (u, v) in enumerate(edges):
        adj[u].append((v, idx))
        adj[v].append((u, idx))

    disc = [-1] * n
    low = [-1] * n
    timer = 0
    stack = []
    comps = []

    def dfs(u, parent_edge):
        nonlocal timer
        disc[u] = low[u] = timer
        timer += 1
        for v, ei in adj[u]:
            if ei == parent_edge:
                continue
            if disc[v] == -1:
                stack.append((u, v))
                dfs(v, ei)
                low[u] = min(low[u], low[v])
                if low[v] >= disc[u]:
                    comp_nodes = set()
                    while stack[-1] != (u, v):
                        a, b = stack.pop()
                        comp_nodes.update((a, b))
                    comp_nodes.update((u, v))
                    stack.pop()
                    comps.append(sorted(comp_nodes))
            elif disc[v] < disc[u]:
                stack.append((u, v))
                low[u] = min(low[u], disc[v])

    for start in range(n):
        if disc[start] == -1:
            dfs(start, -1)
    return comps


if __name__ == "__main__":
    print(biconnected(6, [(0,1),(1,2),(2,0),(1,3),(3,4),(4,5),(5,3)]))
