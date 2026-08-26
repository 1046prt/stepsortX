# Stepsort · Word Break
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/word-break

def word_break(s: str, word_dict: list[str]) -> tuple[bool, list[str]]:
    # Returns whether s can be split into dictionary words and,
    # when possible, one valid segmentation.
    words = set(word_dict)
    n = len(s)
    dp = [False] * (n + 1)   # dp[i]: prefix s[:i] is breakable
    parent = [-1] * (n + 1)  # start index of the word ending at i
    dp[0] = True
    for i in range(1, n + 1):
        for j in range(i):
            if dp[j] and s[j:i] in words:
                dp[i] = True
                parent[i] = j
                break
    if not dp[n]:
        return False, []
    parts, i = [], n
    while i > 0:
        parts.append(s[parent[i]:i])
        i = parent[i]
    return True, parts[::-1]


if __name__ == "__main__":
    dictionary = ["cat", "cats", "and", "sand", "dog"]
    ok, segs = word_break("catsanddog", dictionary)
    print("catsanddog breakable:", ok, "->", " ".join(segs))
    ok, segs = word_break("catsandog", dictionary)
    print("catsandog breakable:", ok, segs)
