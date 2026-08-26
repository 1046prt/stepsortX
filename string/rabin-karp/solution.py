# sortsort · Rabin-Karp
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rabin-karp

BASE = 256   # alphabet size
MOD = 100000007  # prime modulus keeps the rolling hash compact


def rabin_karp_search(text, pattern):
    # Slide a rolling hash over every window of length m.
    # Hash hits are verified by direct comparison, so a collision
    # between different strings can never be reported as a match.
    n, m = len(text), len(pattern)
    matches = []
    if m == 0 or m > n:
        return matches

    high_order = pow(BASE, m - 1, MOD)  # weight of the leading char
    p_hash = 0                          # hash of the pattern
    t_hash = 0                          # hash of the current window
    for i in range(m):
        p_hash = (p_hash * BASE + ord(pattern[i])) % MOD
        t_hash = (t_hash * BASE + ord(text[i])) % MOD

    for start in range(n - m + 1):
        if p_hash == t_hash and text[start:start + m] == pattern:
            matches.append(start)
        if start < n - m:  # roll the window one character to the right
            t_hash = ((t_hash - ord(text[start]) * high_order) * BASE
                      + ord(text[start + m])) % MOD
            if t_hash < 0:
                t_hash += MOD
    return matches


if __name__ == "__main__":
    print(rabin_karp_search("ababcababd", "abab"))
    print(rabin_karp_search("aaaaab", "aa"))
