# sortsort · String Reversal
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/string-reversal

def reverse_string(chars):
    # Two pointers swap characters while moving toward the middle.
    left, right = 0, len(chars) - 1
    while left < right:
        chars[left], chars[right] = chars[right], chars[left]
        left += 1
        right -= 1
    return chars


def reversed_text(text):
    # Python strings are immutable, so work on a list of characters.
    return "".join(reverse_string(list(text)))


if __name__ == "__main__":
    for text in ["hello", "algorithm", "racecar", ""]:
        print(text, "->", reversed_text(text))
