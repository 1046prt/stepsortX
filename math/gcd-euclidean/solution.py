# sortsort · Euclidean GCD
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gcd-euclidean

# Euclidean GCD: iterative modulo loop and recursion; LCM derived from GCD.
def gcd_iterative(a, b):
    while b != 0:
        a, b = b, a % b
    return abs(a)


def gcd_recursive(a, b):
    if b == 0:
        return abs(a)
    return gcd_recursive(b, a % b)


def lcm_from_gcd(a, b):
    # Divide first so intermediate values stay small.
    return a // gcd_iterative(a, b) * b


if __name__ == "__main__":
    pairs = [(48, 18), (100, 75), (17, 13), (270, 192), (7, 7)]
    for x, y in pairs:
        g1 = gcd_iterative(x, y)
        g2 = gcd_recursive(x, y)
        l = lcm_from_gcd(x, y)
        print(f"gcd({x}, {y}) = {g1} (recursive: {g2}), lcm = {l}")
