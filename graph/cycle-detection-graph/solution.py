# Stepsort · Cycle Detection
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cycle-detection-graph

def has_cycle_directed(vertices, adj):
    # Three-color DFS: white = unvisited, gray = in current stack, black = done
    WHITE, GRAY, BLACK = 0, 1, 2
    color = [WHITE] * vertices

    def dfs(u):
        color[u] = GRAY
        for v in adj[u]:
            if color[v] == GRAY:
                return True  # back edge into the current path
            if color[v] == WHITE and dfs(v):
                return True
        color[u] = BLACK
        return False

    for u in range(vertices):
        if color[u] == WHITE and dfs(u):
            return True
    return False


if __name__ == "__main__":
    V = 4
    cyclic_graph = [
        [1],
        [2],
        [0, 3],  # 0 -> 1 -> 2 -> 0 forms a cycle
        [],
    ]
    acyclic_graph = [
        [1, 2],
        [3],
        [3],
        [],
    ]
    print("Graph 1 cyclic:", has_cycle_directed(V, cyclic_graph))
    print("Graph 2 cyclic:", has_cycle_directed(V, acyclic_graph))
