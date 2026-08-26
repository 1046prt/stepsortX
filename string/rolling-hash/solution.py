# sortsort · Rolling Hash (Polynomial)
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rolling-hash

MOD = 10**9 + 7
P = 31


def build_hashes(s):
    h = [0]
    pw = [1]
    for ch in s:
        h.append((h[-1] * P + ord(ch)) % MOD)
        pw.append((pw[-1] * P) % MOD)
    return h, pw


def substring_hash(h, pw, l, r):   # inclusive
    return (h[r + 1] - h[l] * pw[r - l + 1]) % MOD


if __name__ == "__main__":
    s = "abcabd"
    h, pw = build_hashes(s)
    a = substring_hash(h, pw, 0, 2)   # "abc"
    b = substring_hash(h, pw, 3, 5)   # "abd"
    print(f"abc vs abd hashes: {a} vs {b} -> {'match' if a == b else 'differ'}")
    c = substring_hash(h, pw, 0, 1)   # "ab"
    d = substring_hash(h, pw, 3, 4)   # "ab"
    print(f"ab vs ab hashes equal: {c == d}")
