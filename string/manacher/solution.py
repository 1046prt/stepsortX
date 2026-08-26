# sortsort · Manacher's Algorithm
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/manacher

def manacher(s):
    # Linear-time longest palindromic substring via radii on a padded text.
    t = "#" + "#".join(s) + "#"
    n = len(t)
    p = [0] * n
    center, right = 0, 0
    best_len, best_center = 0, 0

    for i in range(n):
        if i < right:
            p[i] = min(right - i, p[2 * center - i])
        while (i - p[i] - 1 >= 0 and i + p[i] + 1 < n
               and t[i - p[i] - 1] == t[i + p[i] + 1]):
            p[i] += 1
        if i + p[i] > right:
            center, right = i, i + p[i]
        if p[i] > best_len:
            best_len, best_center = p[i], i

    start = (best_center - best_len) // 2
    return s[start:start + best_len]


if __name__ == "__main__":
    for text in ["babad", "cbbd", "forgeeksskeegfor"]:
        print(text, "->", manacher(text))
