# sortsort · Aho-Corasick
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/aho-corasick

from collections import deque


def build_automaton(patterns):
    goto = [[-1] * 26]
    out = [[]]
    for idx, pat in enumerate(patterns):
        node = 0
        for ch in pat:
            c = ord(ch) - ord('a')
            if goto[node][c] == -1:
                goto.append([-1] * 26)
                out.append([])
                goto[node][c] = len(goto) - 1
            node = goto[node][c]
        out[node].append(idx)

    fail = [0] * len(goto)
    queue = deque()
    for c in range(26):
        if goto[0][c] == -1:
            goto[0][c] = 0
        else:
            queue.append(goto[0][c])

    while queue:
        r = queue.popleft()
        for c in range(26):
            u = goto[r][c]
            if u == -1:
                goto[r][c] = goto[fail[r]][c]
            else:
                fail[u] = goto[fail[r]][c]
                if fail[u] == u:
                    fail[u] = 0
                out[u] += out[fail[u]]
                queue.append(u)
    return goto, fail, out


def find_matches(text, patterns):
    goto, fail, out = build_automaton(patterns)
    found = []
    state = 0
    for i, ch in enumerate(text):
        state = goto[state][ord(ch) - ord('a')]
        for idx in out[state]:
            found.append((patterns[idx], i - len(patterns[idx]) + 1))
    return found


if __name__ == "__main__":
    text = "ahishers"
    patterns = ["he", "she", "his", "hers"]
    print("text:", text)
    for pat, start in find_matches(text, patterns):
        print(pat, "at index", start)
