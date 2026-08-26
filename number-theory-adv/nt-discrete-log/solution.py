# Stepsort · Discrete Logarithm
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-discrete-log

from math import isqrt


def bsgs(g, h, p):
    # least x >= 0 with g^x = h (mod p); assumes p prime and gcd(g, p) = 1
    m = isqrt(p - 1) + 1
    baby = {}
    cur = 1
    for j in range(m):
        if cur not in baby:
            baby[cur] = j
        cur = cur * g % p
    step = pow(pow(g, m, p), p - 2, p)  # g^-m via Fermat's little theorem
    gamma = h % p
    for i in range(m + 1):
        if gamma in baby:
            return i * m + baby[gamma]
        gamma = gamma * step % p
    return -1


if __name__ == "__main__":
    cases = [(3, 13, 17), (5, 3, 23), (6, 5, 41)]
    for g, h, p in cases:
        x = bsgs(g, h, p)
        ok = x >= 0 and pow(g, x, p) == h % p
        verdict = "verified" if ok else "not found"
        print("log base", g, "of", h, "mod", p, "= x =", x, "|", verdict)
