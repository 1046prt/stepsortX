# Stepsort · Hamming Distance
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamming-distance

def hamming_distance(x, y):
    diff = x ^ y  # bits where x and y differ stay set
    count = 0
    while diff:
        diff &= diff - 1
        count += 1
    return count


if __name__ == "__main__":
    pairs = [(1, 4), (3, 1), (0, 255), (93, 73)]
    for x, y in pairs:
        print(x, "vs", y, "->", hamming_distance(x, y))
