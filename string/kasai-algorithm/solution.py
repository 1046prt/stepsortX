# sortsort · Kasai's Algorithm
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kasai-algorithm

def kasai(s, sa):
    n = len(s)
    rank = [0] * n
    for i, suf in enumerate(sa):
        rank[suf] = i
    lcp = [0] * n
    h = 0
    for i in range(n):
        if rank[i] > 0:
            j = sa[rank[i] - 1]
            while i + h < n and j + h < n and s[i + h] == s[j + h]:
                h += 1
            lcp[rank[i]] = h
            if h > 0:
                h -= 1
        else:
            h = 0
    return lcp


if __name__ == "__main__":
    s = "banana"
    sa = [5, 3, 1, 0, 4, 2]
    print(kasai(s, sa))   # [0, 1, 3, 0, 0, 2]
