# sortsort · KMP Pattern Matching
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kmp

def build_lps(pattern):
    # lps[i] = length of the longest proper prefix of pattern[:i+1]
    # that is also a suffix of it.
    lps = [0] * len(pattern)
    length = 0
    i = 1
    while i < len(pattern):
        if pattern[i] == pattern[length]:
            length += 1
            lps[i] = length
            i += 1
        elif length > 0:
            length = lps[length - 1]
        else:
            i += 1
    return lps


def kmp_search(text, pattern):
    # Scan the text once, falling back along the LPS table on mismatch.
    n, m = len(text), len(pattern)
    matches = []
    if m == 0 or m > n:
        return matches
    lps = build_lps(pattern)
    i = j = 0
    while i < n:
        if text[i] == pattern[j]:
            i += 1
            j += 1
            if j == m:
                matches.append(i - m)
                j = lps[j - 1]
        elif j > 0:
            j = lps[j - 1]
        else:
            i += 1
    return matches


if __name__ == "__main__":
    text = "AABAACAADAABAABA"
    pattern = "AABA"
    print("text:", text)
    print("pattern:", pattern)
    print("lps table:", build_lps(pattern))
    print("found at:", kmp_search(text, pattern))
