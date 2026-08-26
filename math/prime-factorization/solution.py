# sortsort · Prime Factorization
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/prime-factorization

# Trial division: every composite n has a factor no larger than sqrt(n).
def prime_factors(n):
    factors = []
    d = 2
    while d * d <= n:
        while n % d == 0:
            factors.append(d)
            n //= d
        d += 1
    if n > 1:
        factors.append(n)
    return factors


def factorization(n):
    # Same idea, collecting (prime, exponent) pairs.
    groups = []
    d = 2
    while d * d <= n:
        if n % d == 0:
            e = 0
            while n % d == 0:
                n //= d
                e += 1
            groups.append((d, e))
        d += 1
    if n > 1:
        groups.append((n, 1))
    return groups


if __name__ == "__main__":
    for value in (60, 100, 97, 360, 1024):
        print(value, "->", prime_factors(value))
        print("   grouped:", factorization(value))
