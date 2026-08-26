# Stepsort · Z Algorithm
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/z-algorithm

def z_function(s):
    # z[i] = length of the longest common prefix of s and s[i:].
    n = len(s)
    z = [0] * n
    z[0] = n
    left = right = 0  # rightmost match window found so far
    for i in range(1, n):
        if i < right:  # reuse information from the previous window
            z[i] = min(right - i, z[i - left])
        while i + z[i] < n and s[z[i]] == s[i + z[i]]:
            z[i] += 1
        if i + z[i] > right:  # extend the match window
            left, right = i, i + z[i]
    return z


def z_search(text, pattern):
    # A '#' separator never appears in the data, so any maximal
    # prefix match crossing it must end exactly at the separator.
    combined = pattern + "#" + text
    z = z_function(combined)
    m = len(pattern)
    matches = [i - m - 1 for i in range(m + 1, len(combined)) if z[i] == m]
    return combined, z, matches


if __name__ == "__main__":
    text = "aabxaabxcaabxaabxay"
    pattern = "aabx"
    combined, z, matches = z_search(text, pattern)
    print("combined:", combined)
    print("z-array:", z)
    print("matches:", matches)
