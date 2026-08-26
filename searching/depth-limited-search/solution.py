# Stepsort · Depth-Limited Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/depth-limited-search

def depth_limited_search(graph, start, target, limit):
    # DFS capped at the depth limit; returns the path found or None.
    path = []

    def recurse(node, depth):
        path.append(node)
        if node == target:
            return True
        if depth < limit:
            for nxt in graph.get(node, []):
                if nxt not in path and recurse(nxt, depth + 1):
                    return True
        path.pop()
        return False

    return path if recurse(start, 0) else None


if __name__ == "__main__":
    graph = {
        0: [1, 2],
        1: [3, 4],
        2: [5, 6],
        3: [7],
        4: [7],
        5: [],
        6: [],
        7: [],
    }
    print("limit 2 to node 6:", depth_limited_search(graph, 0, 6, 2))
    print("limit 1 to node 7:", depth_limited_search(graph, 0, 7, 1))
