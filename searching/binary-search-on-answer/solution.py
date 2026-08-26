# Stepsort · Binary Search on Answer
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search-on-answer

def min_eating_speed(piles, h):
    def hours_needed(speed):
        return sum((pile + speed - 1) // speed for pile in piles)

    lo, hi = 1, max(piles)
    answer = hi
    while lo <= hi:
        mid = (lo + hi) // 2
        if hours_needed(mid) <= h:
            answer = mid
            hi = mid - 1          # try slower
        else:
            lo = mid + 1          # too slow
    return answer


if __name__ == "__main__":
    print(min_eating_speed([3, 6, 7, 11], 8))       # 4
    print(min_eating_speed([30, 11, 23, 4, 20], 5)) # 30
