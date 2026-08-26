# Stepsort · Power of Two Check
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/power-of-two

def is_power_of_two(n):
    # a power of two has exactly one set bit
    return n > 0 and (n & (n - 1)) == 0


if __name__ == "__main__":
    tests = [0, 1, 2, 3, 16, 31, 64, 100, 128]
    for v in tests:
        print(v, "->", is_power_of_two(v))
