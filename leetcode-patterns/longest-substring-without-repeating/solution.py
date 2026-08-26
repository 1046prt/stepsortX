# sortsort · Longest Substring Without Repeating
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/longest-substring-without-repeating

def longest_unique_substring(s):
    # Sliding window with last-seen index of each character.
    last_seen = {}
    start = 0
    best_len = 0
    best_start = 0
    for i, ch in enumerate(s):
        if ch in last_seen and last_seen[ch] >= start:
            start = last_seen[ch] + 1
        last_seen[ch] = i
        if i - start + 1 > best_len:
            best_len = i - start + 1
            best_start = start
    return best_len, s[best_start:best_start + best_len]


if __name__ == "__main__":
    length, sub = longest_unique_substring("abcabcbb")
    print(length, sub)
