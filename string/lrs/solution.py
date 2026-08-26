# sortsort · Longest Repeated Substring
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lrs

def longest_repeated_substring(s):
    # dp[i][j] = length of common suffix of s[:i], s[:j] with i != j.
    n = len(s)
    dp = [[0] * (n + 1) for _ in range(n + 1)]
    best_len, best_end = 0, 0

    for i in range(1, n + 1):
        for j in range(i + 1, n + 1):
            if s[i - 1] == s[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
                if dp[i][j] > best_len:
                    best_len, best_end = dp[i][j], i

    return s[best_end - best_len:best_end]


if __name__ == "__main__":
    for text in ["banana", "geeksforgeeks", "abcd"]:
        print(text, "->", longest_repeated_substring(text))
