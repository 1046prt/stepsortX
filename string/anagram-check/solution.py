# sortsort · Anagram Check
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/anagram-check

def is_anagram(a, b):
    # Frequency count over the 26 lowercase letters.
    if len(a) != len(b):
        return False
    counts = [0] * 26
    base = ord('a')
    for ch in a:
        counts[ord(ch) - base] += 1
    for ch in b:
        idx = ord(ch) - base
        counts[idx] -= 1
        if counts[idx] < 0:  # b needs a letter a does not have
            return False
    return True  # equal lengths plus no deficit implies no surplus


if __name__ == "__main__":
    pairs = [
        ("listen", "silent"),
        ("triangle", "integral"),
        ("hello", "world"),
        ("aab", "abb"),
    ]
    for x, y in pairs:
        print(x, "vs", y, "->", is_anagram(x, y))
