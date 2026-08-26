# Stepsort · Letter Combinations
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/letter-combinations

phone = {
    "2": "abc", "3": "def", "4": "ghi", "5": "jkl",
    "6": "mno", "7": "pqrs", "8": "tuv", "9": "wxyz",
}


def letter_combinations(digits):
    results = []
    if not digits:
        return results

    def backtrack(idx, current):
        if idx == len(digits):
            results.append(current)
            return
        for ch in phone[digits[idx]]:
            backtrack(idx + 1, current + ch)

    backtrack(0, "")
    return results


if __name__ == "__main__":
    combos = letter_combinations("23")
    print(combos)
    print("Total:", len(combos))
