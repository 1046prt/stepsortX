# sortsort · Counting Bits (DP)
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-counting-dp

def counting_bits(n):
    # dp[i] = set bits of i >> 1 plus the lowest bit of i
    dp = [0] * (n + 1)
    for i in range(1, n + 1):
        dp[i] = dp[i >> 1] + (i & 1)
    return dp


if __name__ == "__main__":
    dp = counting_bits(16)
    for i, ones in enumerate(dp):
        print(i, "->", ones)
