# sortsort · Edit Distance
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/edit-distance

def min_distance(word1: str, word2: str) -> int:
    # Minimum insert/delete/replace operations to turn word1 into word2
    m, n = len(word1), len(word2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(m + 1):
        dp[i][0] = i  # delete all i characters
    for j in range(n + 1):
        dp[0][j] = j  # insert all j characters
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if word1[i - 1] == word2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            else:
                dp[i][j] = 1 + min(dp[i - 1][j],      # delete
                                   dp[i][j - 1],      # insert
                                   dp[i - 1][j - 1])  # replace
    return dp[m][n]


if __name__ == "__main__":
    print("horse -> ros:", min_distance("horse", "ros"))
    print("intention -> execution:", min_distance("intention", "execution"))
