# Stepsort · Iterative Deepening DFS
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/iterative-deepening

def depth_limited(graph, start, target, limit):
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


def iterative_deepening(graph, start, target, max_limit):
    # Re-run depth-limited search with limits 0..max_limit until found.
    for limit in range(max_limit + 1):
        result = depth_limited(graph, start, target, limit)
        if result is not None:
            return limit, result
    return -1, []


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
    depth, path = iterative_deepening(graph, 0, 7, 4)
    print("target 7 found at depth:", depth, "path:", path)
    depth, path = iterative_deepening(graph, 0, 5, 4)
    print("target 5 found at depth:", depth, "path:", path)
