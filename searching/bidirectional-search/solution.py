# Stepsort · Bidirectional Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bidirectional-search

from collections import deque


def bfs_level(graph, queue, mine, theirs):
    # Expand one whole BFS level; return a node the other side saw.
    for _ in range(len(queue)):
        node = queue.popleft()
        for nxt in graph.get(node, []):
            if nxt not in mine:
                mine[nxt] = node
                queue.append(nxt)
                if nxt in theirs:
                    return nxt
    return None


def build_path(src_parent, dst_parent, meet):
    # Stitch the source-to-meet and meet-to-target halves together.
    head = []
    node = meet
    while node is not None:
        head.append(node)
        node = src_parent[node]
    head.reverse()
    tail = []
    node = dst_parent[meet]
    while node is not None:
        tail.append(node)
        node = dst_parent[node]
    return head + tail


def bidirectional_search(graph, source, target):
    # BFS from both ends at once, always growing the smaller frontier.
    if source == target:
        return source, [source]
    src_parent = {source: None}
    dst_parent = {target: None}
    src_queue = deque([source])
    dst_queue = deque([target])
    while src_queue and dst_queue:
        if len(src_queue) <= len(dst_queue):
            meet = bfs_level(graph, src_queue, src_parent, dst_parent)
        else:
            meet = bfs_level(graph, dst_queue, dst_parent, src_parent)
        if meet is not None:
            return meet, build_path(src_parent, dst_parent, meet)
    return -1, []


if __name__ == "__main__":
    graph = {
        0: [1, 2],
        1: [0, 3, 4],
        2: [0, 5, 6],
        3: [1, 7],
        4: [1, 7],
        5: [2],
        6: [2],
        7: [3, 4],
    }
    meet, path = bidirectional_search(graph, 0, 7)
    print("meeting node:", meet)
    print("path found:", meet != -1)
    print("path:", path)
    meet, path = bidirectional_search(graph, 0, 99)
    print("search for 99 -> meeting node:", meet, "path found:", meet != -1)
