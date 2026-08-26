# Stepsort · Tarjan's SCC
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tarjan-scc

def tarjan_scc(vertices, adj):
    counter = [0]
    index = [-1] * vertices
    low = [0] * vertices
    on_stack = [False] * vertices
    stack = []
    components = []

    def strongconnect(u):
        index[u] = low[u] = counter[0]
        counter[0] += 1
        stack.append(u)
        on_stack[u] = True
        for v in adj[u]:
            if index[v] == -1:
                strongconnect(v)
                low[u] = min(low[u], low[v])
            elif on_stack[v]:
                low[u] = min(low[u], index[v])
        if low[u] == index[u]:  # u is the root of an SCC
            component = []
            while True:
                w = stack.pop()
                on_stack[w] = False
                component.append(w)
                if w == u:
                    break
            components.append(component)

    for u in range(vertices):
        if index[u] == -1:
            strongconnect(u)
    return components


if __name__ == "__main__":
    V = 8
    adj = [
        [1],      # 0
        [2],      # 1  cycle 0-1-2
        [0, 3],   # 2
        [4],      # 3
        [3, 5],   # 4  cycle 3-4
        [],       # 5
        [7, 4],   # 6
        [5, 6],   # 7  cycle 6-7
    ]
    for component in tarjan_scc(V, adj):
        print("SCC:", sorted(component))
