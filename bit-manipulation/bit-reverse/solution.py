# sortsort · Reverse Bits
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-reverse

def reverse_bits(n):
    n &= 0xFFFFFFFF  # normalize to 32-bit unsigned
    result = 0
    for _ in range(32):
        result = (result << 1) | (n & 1)
        n >>= 1
    return result


if __name__ == "__main__":
    samples = [1, 43261596, 4294967280]
    for v in samples:
        print(v, "->", reverse_bits(v))
