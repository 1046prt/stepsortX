# Stepsort · M-Coloring Problem
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/graph-coloring-bt

graph = [
    [0, 1, 0, 1],
    [1, 0, 1, 0],
    [0, 1, 0, 1],
    [1, 0, 1, 0],
]
V = 4
M = 3


def is_safe(v, colors, c):
    for u in range(V):
        if graph[v][u] == 1 and colors[u] == c:
            return False
    return True


def color_graph(colors, v):
    if v == V:
        return True
    for c in range(1, M + 1):
        if is_safe(v, colors, c):
            colors[v] = c
            if color_graph(colors, v + 1):
                return True
            colors[v] = 0
    return False


if __name__ == "__main__":
    colors = [0] * V
    if color_graph(colors, 0):
        print("Color assignment:", colors)
    else:
        print("Not possible with", M, "colors")
