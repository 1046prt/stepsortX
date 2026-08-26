# Stepsort · Miller-Rabin (Randomized)
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-miller-rabin

import random


def miller_rabin(n, rounds=8):
    # Randomized primality test: picks random bases a and checks
    # a^d = +-1 or reaches -1 by repeated squaring.
    if n < 2:
        return False
    for p in [2, 3, 5, 7, 11, 13]:
        if n % p == 0:
            return n == p
    d = n - 1
    r = 0
    while d % 2 == 0:
        d //= 2
        r += 1
    for _ in range(rounds):
        a = random.randrange(2, n - 1)
        x = pow(a, d, n)
        if x == 1 or x == n - 1:
            continue
        composite = True
        for _ in range(r - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                composite = False
                break
        if composite:
            return False  # a is a witness that n is composite
    return True


if __name__ == "__main__":
    random.seed(42)
    tests = [97, 561, 7919, 1105, 999983]
    for n in tests:
        verdict = "prime" if miller_rabin(n) else "composite"
        print(n, "is", verdict)
    # 561 and 1105 are Carmichael numbers: fool Fermat, not Miller-Rabin.
