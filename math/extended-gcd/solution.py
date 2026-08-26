# sortsort · Extended Euclidean GCD
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/extended-gcd

def extended_gcd(a, b):
    # returns (g, x, y) with a*x + b*y = g = gcd(a, b)
    if b == 0:
        return a, 1, 0
    g, x1, y1 = extended_gcd(b, a % b)
    return g, y1, x1 - (a // b) * y1


if __name__ == "__main__":
    pairs = [(240, 46), (30, 20), (17, 5), (998244353, 1000000000)]
    for a, b in pairs:
        g, x, y = extended_gcd(a, b)
        print(f"{a}*({x}) + {b}*({y}) = {g}")
        assert a * x + b * y == g
