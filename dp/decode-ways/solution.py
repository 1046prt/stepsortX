# Stepsort · Decode Ways
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/decode-ways

def num_decodings(s: str) -> int:
    if not s or s[0] == "0":
        return 0
    # prev2 = ways for prefix ending two chars back, prev1 = one char back
    prev2, prev1 = 1, 1
    for i in range(1, len(s)):
        cur = 0
        if s[i] != "0":  # single digit decode
            cur += prev1
        two = int(s[i - 1:i + 1])
        if 10 <= two <= 26:  # two digit decode
            cur += prev2
        prev2, prev1 = prev1, cur
    return prev1


if __name__ == "__main__":
    tests = ["12", "226", "06"]
    for t in tests:
        print(t, "decodes in", num_decodings(t), "ways")
