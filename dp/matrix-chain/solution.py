# sortsort · Matrix Chain Multiplication
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/matrix-chain

def matrix_chain_order(dims):
    # Matrix i has dimensions dims[i] x dims[i+1]
    n = len(dims) - 1
    dp = [[0] * n for _ in range(n)]
    split = [[0] * n for _ in range(n)]
    for length in range(2, n + 1):  # chain lengths
        for i in range(n - length + 1):
            j = i + length - 1
            dp[i][j] = float("inf")
            for k in range(i, j):
                cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1]
                if cost < dp[i][j]:
                    dp[i][j] = cost
                    split[i][j] = k
    return dp[0][n - 1], split


def build_parens(split, i, j):
    # Rebuild the optimal parenthesization string
    if i == j:
        return chr(ord("A") + i)
    k = split[i][j]
    left = build_parens(split, i, k)
    right = build_parens(split, k + 1, j)
    return "(" + left + right + ")"


if __name__ == "__main__":
    dims = [10, 30, 5, 60]
    cost, split = matrix_chain_order(dims)
    print("Minimum scalar multiplications:", cost)
    print("Optimal parenthesization:", build_parens(split, 0, len(dims) - 2))
