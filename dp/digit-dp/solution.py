# sortsort · Digit DP
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/digit-dp

from functools import lru_cache


def count_nondecreasing(limit: str) -> int:
    digits = [int(ch) for ch in limit]
    n = len(digits)

    @lru_cache(maxsize=None)
    def dfs(pos, prev_digit, tight, started):
        if pos == n:
            return 1 if started else 0
        max_d = digits[pos] if tight else 9
        total = 0
        for d in range(max_d + 1):
            if started and d < prev_digit:
                continue
            total += dfs(pos + 1, d, tight and d == max_d,
                         started or d > 0)
        return total

    result = dfs(0, 0, True, False)
    dfs.cache_clear()
    return result


if __name__ == "__main__":
    print(count_nondecreasing("356"))     # 84
    print(count_nondecreasing("100"))     # 46
