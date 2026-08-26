# sortsort · Boyer-Moore
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boyer-moore

def bad_character_table(pattern):
    # Last index of each character inside the pattern.
    table = {}
    for i in range(len(pattern)):
        table[pattern[i]] = i
    return table


def boyer_moore_search(text, pattern):
    n, m = len(text), len(pattern)
    if m == 0 or m > n:
        return []
    bad = bad_character_table(pattern)
    matches = []
    shift = 0

    while shift <= n - m:
        j = m - 1
        while j >= 0 and pattern[j] == text[shift + j]:
            j -= 1
        if j < 0:
            matches.append(shift)
            next_index = shift + m
            if next_index < n:
                shift += m - bad.get(text[next_index], -1)
            else:
                shift += 1
        else:
            shift += max(1, j - bad.get(text[shift + j], -1))
    return matches


if __name__ == "__main__":
    print(boyer_moore_search("ABAAABCDABABCD", "ABC"))
    print(boyer_moore_search("AABAACAADAABAABA", "AABA"))
