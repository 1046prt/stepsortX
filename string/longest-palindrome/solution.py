# sortsort · Longest Palindromic Substring
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/longest-palindrome

def expand(s, left, right):
    # Grow the window while it stays a palindrome and stays in bounds.
    # Returns the start index and length of the widest palindrome found.
    while left >= 0 and right < len(s) and s[left] == s[right]:
        left -= 1
        right += 1
    return left + 1, right - left - 1


def longest_palindrome(s):
    # Every palindrome has a center: a character (odd length) or a gap
    # between two characters (even length). Try all 2n - 1 centers.
    best_start, best_len = 0, 0
    for center in range(len(s)):
        odd_start, odd_len = expand(s, center, center)
        even_start, even_len = expand(s, center, center + 1)
        if odd_len > best_len:
            best_start, best_len = odd_start, odd_len
        if even_len > best_len:
            best_start, best_len = even_start, even_len
    return s[best_start:best_start + best_len]


if __name__ == "__main__":
    for text in ["babad", "cbbd", "forgeeksskeegfor"]:
        print(text, "->", longest_palindrome(text))
