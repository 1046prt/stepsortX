# Stepsort · Quadratic Residue
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-quadratic-residue

def legendre(a, p):
    # Euler's criterion: a^((p-1)/2) mod p is 0, 1 or p-1
    r = pow(a % p, (p - 1) // 2, p)
    if r == 0:
        return 0
    return 1 if r == 1 else -1


def tonelli_shanks(a, p):
    # square root of residue a modulo odd prime p
    a %= p
    if a == 0:
        return 0
    if p % 4 == 3:
        return pow(a, (p + 1) // 4, p)
    q, s = p - 1, 0
    while q % 2 == 0:
        q //= 2
        s += 1
    z = 2
    while legendre(z, p) != -1:
        z += 1
    m, c = s, pow(z, q, p)              # c is a non-residue^q
    t, r = pow(a, q, p), pow(a, (q + 1) // 2, p)
    while t != 1:
        i, t2 = 0, t
        while t2 != 1:
            t2 = t2 * t2 % p
            i += 1
        b = pow(c, 1 << (m - i - 1), p)
        m, c, t, r = i, b * b % p, t * b * b % p, r * b % p
    return r


if __name__ == "__main__":
    p = 13
    names = {0: "zero", 1: "a quadratic residue", -1: "a non-residue"}
    for a in (3, 5, 10):
        e = legendre(a, p)
        print(a, "mod", p, "is", names[e])
        if e == 1:
            root = tonelli_shanks(a, p)
            print("  sqrt =", root, "and", p - root,
                  "| check:", (root * root) % p == a % p)
    p = 17
    root = tonelli_shanks(2, p)
    print("sqrt of 2 mod", p, "=", root, "| check:", (root * root) % p == 2)
