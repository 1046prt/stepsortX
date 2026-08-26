# sortsort · Booth's Algorithm
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/booth-minimal-rotation

def booth(s):
    s = s + s
    n = len(s) // 2
    f = [-1] * len(s)
    k = 0
    for j in range(1, len(s)):
        i = f[j - k - 1]
        while i != -1 and s[j] != s[k + i + 1]:
            if s[j] < s[k + i + 1]:
                k = j - i - 1
            i = f[i]
        if i == -1 and s[j] != s[k]:
            if s[j] < s[k]:
                k = j
            f[j - k] = -1
        else:
            f[j - k] = i + 1
    return k

s = "cdefab"
pos = booth(s)
print("Minimal rotation:", s[pos:] + s[:pos])
