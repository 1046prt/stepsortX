# Stepsort · Levenshtein Distance
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/levenshtein

def levenshtein_distance(a, b):
    # Full DP table: dp[i][j] = edits to turn a[:i] into b[:j].
    rows, cols = len(a) + 1, len(b) + 1
    dp = [[0] * cols for _ in range(rows)]
    for i in range(rows):
        dp[i][0] = i
    for j in range(cols):
        dp[0][j] = j

    for i in range(1, rows):
        for j in range(1, cols):
            cost = 0 if a[i - 1] == b[j - 1] else 1
            dp[i][j] = min(dp[i - 1][j] + 1,          # deletion
                           dp[i][j - 1] + 1,          # insertion
                           dp[i - 1][j - 1] + cost)   # substitution
    return dp[rows - 1][cols - 1]


if __name__ == "__main__":
    pairs = [("kitten", "sitting"), ("flaw", "lawn"), ("", "abc")]
    for x, y in pairs:
        print(x, "vs", y, "->", levenshtein_distance(x, y))
