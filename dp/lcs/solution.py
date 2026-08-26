# sortsort · Longest Common Subsequence
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lcs

def lcs(s1, s2):
    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    parent = [[0] * (n + 1) for _ in range(m + 1)]  # 1=diag 2=up 3=left
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
                parent[i][j] = 1
            elif dp[i - 1][j] >= dp[i][j - 1]:
                dp[i][j] = dp[i - 1][j]
                parent[i][j] = 2
            else:
                dp[i][j] = dp[i][j - 1]
                parent[i][j] = 3
    # Follow parent pointers from (m, n) back to (0, 0)
    chars = []
    i, j = m, n
    while i > 0 and j > 0:
        if parent[i][j] == 1:
            chars.append(s1[i - 1])
            i -= 1
            j -= 1
        elif parent[i][j] == 2:
            i -= 1
        else:
            j -= 1
    chars.reverse()
    return dp[m][n], "".join(chars)


if __name__ == "__main__":
    s1, s2 = "AGGTAB", "GXTXAYB"
    length, sub = lcs(s1, s2)
    print(f"LCS of {s1} and {s2}: length={length}, sequence={sub}")
