# sortsort · Bridges & Articulation Points
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bridges-articulation

def bridges_and_articulation(vertices, adj):
    timer = [0]
    disc = [-1] * vertices  # discovery times; -1 = unvisited
    low = [0] * vertices    # lowest discovery time reachable
    bridges = []
    articulation = set()

    def dfs(u, parent):
        disc[u] = low[u] = timer[0]
        timer[0] += 1
        children = 0
        for v in adj[u]:
            if v == parent:
                continue
            if disc[v] == -1:
                children += 1
                dfs(v, u)
                low[u] = min(low[u], low[v])
                if low[v] > disc[u]:
                    bridges.append((u, v))          # no back edge over this edge
                if parent != -1 and low[v] >= disc[u]:
                    articulation.add(u)             # subtree cannot bypass u
            else:
                low[u] = min(low[u], disc[v])       # back edge
        if parent == -1 and children > 1:
            articulation.add(u)                     # root with separated subtrees

    for u in range(vertices):
        if disc[u] == -1:
            dfs(u, -1)
    return bridges, sorted(articulation)


if __name__ == "__main__":
    V = 7
    adj = [
        [1, 2],
        [0, 2],
        [0, 1, 3],
        [2, 4],
        [3, 5, 6],
        [4],
        [4],
    ]
    bridges, points = bridges_and_articulation(V, adj)
    print("Bridges:", bridges)
    print("Articulation points:", points)
