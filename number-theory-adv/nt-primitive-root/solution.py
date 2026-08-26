# sortsort · Primitive Root
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-primitive-root

def prime_factors(m):
    fs = []
    d = 2
    while d * d <= m:
        if m % d == 0:
            fs.append(d)
            while m % d == 0:
                m //= d
        d += 1
    if m > 1:
        fs.append(m)
    return fs


def primitive_root(p):
    # g generates iff g^((p-1)/q) != 1 for every prime q dividing p-1
    if p == 2:
        return 1
    phi = p - 1
    fs = prime_factors(phi)
    for g in range(2, p):
        if all(pow(g, phi // q, p) != 1 for q in fs):
            return g
    return -1


if __name__ == "__main__":
    for p in (2, 3, 7, 13, 31, 97):
        g = primitive_root(p)
        ok = pow(g, p - 1, p) == 1 and all(pow(g, k, p) != 1 for k in range(1, p - 1))
        print("smallest primitive root mod", p, "=", g, "| order check:", ok)
