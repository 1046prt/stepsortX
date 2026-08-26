# sortsort · Karatsuba Multiplication
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/karatsuba

def karatsuba(x, y):
    # multiply two non-negative integers by splitting around 10^half
    if x < 10 or y < 10:
        return x * y
    digits = max(len(str(x)), len(str(y)))
    half = digits // 2
    power = 10 ** half
    x_high, x_low = divmod(x, power)
    y_high, y_low = divmod(y, power)
    z0 = karatsuba(x_low, y_low)
    z2 = karatsuba(x_high, y_high)
    z1 = karatsuba(x_high + x_low, y_high + y_low) - z2 - z0
    return z2 * 10 ** (2 * half) + z1 * power + z0


if __name__ == "__main__":
    samples = [
        (123456789, 987654321),
        (2147483647, 3037000499),
    ]
    for a, b in samples:
        print(a, "*", b, "=", karatsuba(a, b))
