# sortsort · Knuth Optimization (Optimal BST)
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knuth-optimization

def optimal_bst(freq):
    n = len(freq)
    pre = [0]
    for f in freq:
        pre.append(pre[-1] + f)

    def rng(i, j):   # inclusive frequencies
        return pre[j + 1] - pre[i]

    INF = float("inf")
    dp = [[INF] * (n + 2) for _ in range(n + 2)]
    root = [[0] * (n + 2) for _ in range(n + 2)]
    for i in range(1, n + 1):
        dp[i][i] = freq[i - 1]
        root[i][i] = i - 1

    for length in range(2, n + 1):
        for i in range(1, n - length + 2):
            j = i + length - 1
            lo = root[i][j - 1]
            hi = root[i + 1][j]
            for k in range(lo, hi + 1):
                left = dp[i][k - 1] if k > i else 0
                right = dp[k + 1][j] if k < j else 0
                cost = left + right + rng(i - 1, j)
                if cost < dp[i][j]:
                    dp[i][j] = cost
                    root[i][j] = k
    return dp[1][n]


if __name__ == "__main__":
    print(optimal_bst([4, 2, 6, 3]))   # 26
