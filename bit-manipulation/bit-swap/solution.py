# Stepsort · Swap Without Temp
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-swap

def xor_swap(a, b):
    # three XORs exchange the values without a temporary
    a ^= b
    b ^= a
    a ^= b
    return a, b


if __name__ == "__main__":
    x, y = 3, 9
    print("before:", x, y)
    x, y = xor_swap(x, y)
    print("after:", x, y)
