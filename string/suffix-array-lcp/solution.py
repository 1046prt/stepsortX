# Stepsort · Suffix Array + LCP
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-array-lcp

def build_sa(s):
    n = len(s)
    sa = list(range(n))
    rank = [ord(c) for c in s]
    k = 1
    while k < n:
        sa.sort(key=lambda x: (rank[x], rank[x + k] if x + k < n else -1))
        tmp = [0] * n
        for i in range(1, n):
            prev, cur = sa[i - 1], sa[i]
            tmp[cur] = tmp[prev] + (
                1 if (rank[prev], rank[prev + k] if prev + k < n else -1) <
                     (rank[cur], rank[cur + k] if cur + k < n else -1) else 0
            )
        rank = tmp
        if rank[sa[-1]] == n - 1:
            break
        k *= 2
    return sa

def build_lcp(s, sa):
    n = len(s)
    rank = [0] * n
    for i in range(n):
        rank[sa[i]] = i
    h = 0
    lcp = [0] * n
    for i in range(n):
        if rank[i] > 0:
            j = sa[rank[i] - 1]
            while i + h < n and j + h < n and s[i + h] == s[j + h]:
                h += 1
            lcp[rank[i]] = h
            if h > 0:
                h -= 1
    return lcp

sa = build_sa("banana")
lcp = build_lcp("banana", sa)
print("SA:", sa)
print("LCP:", lcp)
