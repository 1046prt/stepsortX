# sortsort · Word Search
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/word-search

grid = [
    ["A", "B", "C", "E"],
    ["S", "F", "C", "S"],
    ["A", "D", "E", "E"],
]


def exist(word):
    rows, cols = len(grid), len(grid[0])

    def dfs(r, c, idx):
        if idx == len(word):
            return True
        if r < 0 or r >= rows or c < 0 or c >= cols or grid[r][c] != word[idx]:
            return False
        saved = grid[r][c]
        grid[r][c] = "#"  # mark visited
        found = (dfs(r + 1, c, idx + 1) or dfs(r - 1, c, idx + 1) or
                 dfs(r, c + 1, idx + 1) or dfs(r, c - 1, idx + 1))
        grid[r][c] = saved  # unmark
        return found

    for r in range(rows):
        for c in range(cols):
            if dfs(r, c, 0):
                return True
    return False


if __name__ == "__main__":
    for w in ["ABCCED", "SEE", "ABCB"]:
        print(w, "->", exist(w))
