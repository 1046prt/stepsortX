# Stepsort · Palindrome Partitioning
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/palindrome-partition

def min_cut(s: str) -> int:
    n = len(s)
    if n <= 1:
        return 0
    # is_pal[i][j] is True when s[i..j] is a palindrome
    is_pal = [[False] * n for _ in range(n)]
    for i in range(n - 1, -1, -1):
        for j in range(i, n):
            if s[i] == s[j] and (j - i < 2 or is_pal[i + 1][j - 1]):
                is_pal[i][j] = True
    # cut[j] = minimum cuts needed for prefix s[0..j]
    cut = [0] * n
    for j in range(n):
        if is_pal[0][j]:
            cut[j] = 0
            continue
        best = j  # worst case: cut between every pair of characters
        for i in range(1, j + 1):
            if is_pal[i][j]:
                best = min(best, cut[i - 1] + 1)
        cut[j] = best
    return cut[n - 1]


if __name__ == "__main__":
    print("Min cuts for 'aab':", min_cut("aab"))
