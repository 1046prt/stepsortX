# sortsort · Graph Coloring
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/graph-coloring

def greedy_coloring(vertices, adj):
    # Assign each vertex the smallest color not used by its colored neighbors
    result = [-1] * vertices
    for u in range(vertices):
        neighbor_colors = {result[v] for v in adj[u] if result[v] != -1}
        color = 0
        while color in neighbor_colors:
            color += 1
        result[u] = color
    return result


if __name__ == "__main__":
    V = 5
    # Undirected triangle 0-1-2 plus tail 3-4
    adj = [
        [1, 2],
        [0, 2, 3],
        [0, 1, 3],
        [1, 2, 4],
        [3],
    ]
    colors = greedy_coloring(V, adj)
    for v in range(V):
        print("Vertex", v, "-> color", colors[v])
    print("Total colors used:", max(colors) + 1)
