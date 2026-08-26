# Stepsort · Hamiltonian Cycle (BT)
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamiltonian-bt

graph = [
    [0, 1, 0, 1, 0],
    [1, 0, 1, 1, 1],
    [0, 1, 0, 0, 1],
    [1, 1, 0, 0, 1],
    [0, 1, 1, 1, 0],
]
V = 5


def is_safe(v, path, pos):
    if graph[path[pos - 1]][v] == 0:
        return False
    return v not in path


def ham_cycle(path, pos):
    if pos == V:
        return graph[path[pos - 1]][path[0]] == 1
    for v in range(1, V):
        if is_safe(v, path, pos):
            path[pos] = v
            if ham_cycle(path, pos + 1):
                return True
            path[pos] = -1
    return False


if __name__ == "__main__":
    path = [-1] * V
    path[0] = 0
    if ham_cycle(path, 1):
        print("Hamiltonian cycle:", path + [path[0]])
    else:
        print("No Hamiltonian cycle exists")
