# sortsort · Maximal Square
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/maximal-square

def maximal_square(matrix):
    rows, cols = len(matrix), len(matrix[0])
    side = 0
    # dp[i][j] = side of largest square whose bottom-right corner is (i-1, j-1)
    dp = [[0] * (cols + 1) for _ in range(rows + 1)]
    for i in range(rows):
        for j in range(cols):
            if matrix[i][j] == "1":
                dp[i + 1][j + 1] = min(dp[i][j], dp[i + 1][j], dp[i][j + 1]) + 1
                side = max(side, dp[i + 1][j + 1])
    return side * side


if __name__ == "__main__":
    matrix = [
        ["1", "0", "1", "0", "0"],
        ["1", "0", "1", "1", "1"],
        ["1", "1", "1", "1", "1"],
        ["1", "0", "0", "1", "0"],
    ]
    print("Largest square area:", maximal_square(matrix))
