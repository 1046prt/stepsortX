# sortsort · Aho-Corasick Failure Links
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/aho-corasick-failure-links

from collections import deque


def build_aho(patterns):
    goto = [{}]
    fail = [0]
    output = [[]]
    for pat in patterns:
        cur = 0
        for ch in pat:
            if ch not in goto[cur]:
                goto[cur][ch] = len(goto)
                goto.append({})
                fail.append(0)
                output.append([])
            cur = goto[cur][ch]
        output[cur].append(pat)

    queue = deque()
    for ch, nxt in goto[0].items():
        fail[nxt] = 0
        queue.append(nxt)
    while queue:
        u = queue.popleft()
        for ch, v in goto[u].items():
            f = fail[u]
            while f != 0 and ch not in goto[f]:
                f = fail[f]
            fail[v] = goto[f].get(ch, 0) if v != goto[f].get(ch, 0) else 0
            output[v] += output[fail[v]]
            queue.append(v)
    return goto, fail, output


if __name__ == "__main__":
    goto, fail, output = build_aho(["he", "she", "his", "hers"])
    text = "ushers"
    cur = 0
    matches = []
    for i, ch in enumerate(text):
        while cur != 0 and ch not in goto[cur]:
            cur = fail[cur]
        cur = goto[cur].get(ch, 0)
        for pat in output[cur]:
            matches.append(f"{pat} @ {i - len(pat) + 1}..{i}")
    print(matches)   # she @1..3, he @2..3, hers @2..5
