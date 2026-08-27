# Stepsort · Multi-Source BFS
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-source-bfs

from collections import deque

def multi_source_bfs(grid, sources):
    rows, cols = len(grid), len(grid[0])
    dist = [[-1] * cols for _ in range(rows)]
    queue = deque()
    for r, c in sources:
        dist[r][c] = 0
        queue.append((r, c))
    directions = [(0,1),(0,-1),(1,0),(-1,0)]
    while queue:
        r, c = queue.popleft()
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols and dist[nr][nc] == -1:
                dist[nr][nc] = dist[r][c] + 1
                queue.append((nr, nc))
    return dist

if __name__ == "__main__":
    grid = [[0]*4 for _ in range(3)]
    sources = [(0,0), (2,3)]
    for row in multi_source_bfs(grid, sources):
        print(row)
