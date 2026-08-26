# sortsort · Minimum Path Sum
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-path-sum

def min_path_sum(grid):
    if not grid or not grid[0]:
        return 0
    rows, cols = len(grid), len(grid[0])
    # dp[j] = min path sum to reach cell in current row, column j
    dp = [0] * cols
    dp[0] = grid[0][0]
    for j in range(1, cols):
        dp[j] = dp[j - 1] + grid[0][j]
    for i in range(1, rows):
        dp[0] += grid[i][0]
        for j in range(1, cols):
            dp[j] = min(dp[j], dp[j - 1]) + grid[i][j]
    return dp[cols - 1]


if __name__ == "__main__":
    grid = [
        [1, 3, 1],
        [1, 5, 1],
        [4, 2, 1],
    ]
    print("Minimum path sum:", min_path_sum(grid))
