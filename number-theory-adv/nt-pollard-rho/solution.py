# sortsort · Pollard's Rho
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-pollard-rho

import math
import random

BASES = (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)


def is_prime(n):
    # deterministic Miller-Rabin, valid for all 64-bit integers
    if n < 2:
        return False
    for p in BASES:
        if n % p == 0:
            return n == p
    d, s = n - 1, 0
    while d % 2 == 0:
        d //= 2
        s += 1
    for a in BASES:
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


def pollard_rho(n):
    # Floyd cycle detection on f(x) = x*x + c (mod n)
    if n % 2 == 0:
        return 2
    while True:
        c = random.randrange(1, n)
        x = random.randrange(0, n)
        y, d = x, 1
        while d == 1:
            x = (x * x + c) % n
            y = (y * y + c) % n
            y = (y * y + c) % n
            d = math.gcd(abs(x - y), n)
        if d != n:
            return d


def factor(n, out):
    if n == 1:
        return
    if is_prime(n):
        out.append(n)
        return
    d = pollard_rho(n)
    factor(d, out)
    factor(n // d, out)


if __name__ == "__main__":
    random.seed(12345)
    for n in (91, 8051, 10403, 9973 * 10007):
        fs = []
        factor(n, fs)
        fs.sort()
        print(n, "=", " * ".join(map(str, fs)))
