# Stepsort · Flood Fill
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/flood-fill

def flood_fill(grid, sr, sc, new_color):
    rows, cols = len(grid), len(grid[0])
    original = grid[sr][sc]
    if original == new_color:
        return grid
    def dfs(r, c):
        if r < 0 or r >= rows or c < 0 or c >= cols:
            return
        if grid[r][c] != original:
            return
        grid[r][c] = new_color
        dfs(r+1, c); dfs(r-1, c); dfs(r, c+1); dfs(r, c-1)
    dfs(sr, sc)
    return grid

if __name__ == "__main__":
    grid = [[1,1,1],[1,1,0],[1,0,1]]
    print("before:", grid)
    flood_fill(grid, 1, 1, 2)
    print("after: ", grid)
