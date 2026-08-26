# sortsort · A* Search
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/a-star

import heapq


def a_star(rows, cols, blocked, start, goal):
    # Nodes are ids 0..rows*cols-1; node id maps to (row, col) by divmod
    def pos(node):
        return divmod(node, cols)

    # Manhattan distance heuristic (admissible on a unit grid)
    def h(node):
        r, c = pos(node)
        gr, gc = pos(goal)
        return abs(r - gr) + abs(c - gc)

    open_heap = [(h(start), 0, start)]  # (f = g + h, g, node)
    g_cost = {start: 0}
    parent = {start: -1}
    steps = ((1, 0), (-1, 0), (0, 1), (0, -1))

    while open_heap:
        f, g, cur = heapq.heappop(open_heap)
        if g > g_cost[cur]:
            continue  # stale entry
        if cur == goal:
            path = []
            while cur != -1:
                path.append(cur)
                cur = parent[cur]
            return path[::-1], g
        r, c = pos(cur)
        for dr, dc in steps:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols and (nr, nc) not in blocked:
                nxt = nr * cols + nc
                ng = g + 1
                if ng < g_cost.get(nxt, float("inf")):
                    g_cost[nxt] = ng
                    parent[nxt] = cur
                    heapq.heappush(open_heap, (ng + h(nxt), ng, nxt))
    return None, -1


if __name__ == "__main__":
    rows, cols = 4, 5
    blocked = {(1, 1), (1, 3), (2, 2), (3, 1)}
    path, cost = a_star(rows, cols, blocked, 0, 19)  # (0,0) to (3,4)
    print("Path:", path)
    print("Cost:", cost)
