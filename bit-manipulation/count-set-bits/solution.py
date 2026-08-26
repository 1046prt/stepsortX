# Stepsort · Count Set Bits
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-set-bits

def count_set_bits(n):
    # Brian Kernighan: n & (n - 1) clears the lowest set bit
    count = 0
    while n:
        n &= n - 1
        count += 1
    return count


if __name__ == "__main__":
    for v in [0, 1, 7, 13, 255, 1023]:
        print(v, "kernighan:", count_set_bits(v), "builtin:", bin(v).count("1"))
