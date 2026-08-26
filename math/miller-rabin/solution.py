# sortsort · Miller-Rabin Primality
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/miller-rabin

WITNESSES = [2, 3, 5, 7, 11, 13, 17]


def is_prime(n):
    # deterministic for all n < 341550071728321 with this witness set
    if n < 2:
        return False
    for p in WITNESSES:
        if n % p == 0:
            return n == p
    d = n - 1
    s = 0
    while d % 2 == 0:
        d //= 2
        s += 1
    for a in WITNESSES:
        x = pow(a, d, n)
        if x == 1 or x == n - 1:
            continue
        for _ in range(s - 1):
            x = x * x % n
            if x == n - 1:
                break
        else:
            return False
    return True


if __name__ == "__main__":
    tests = [1, 2, 97, 561, 7919, 3215031751, 2147483647,
             67280421310721, 998244359987710471, 9223372036854775783]
    for n in tests:
        verdict = "prime" if is_prime(n) else "composite"
        print(f"{n} is {verdict}")
