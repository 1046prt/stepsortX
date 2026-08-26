# sortsort · Chinese Remainder Theorem
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/chinese-remainder

def extended_gcd(a, b):
    # returns (g, x, y) with a*x + b*y = g = gcd(a, b)
    if b == 0:
        return a, 1, 0
    g, x1, y1 = extended_gcd(b, a % b)
    return g, y1, x1 - (a // b) * y1


def mod_inverse(a, m):
    g, x, _ = extended_gcd(a % m, m)
    if g != 1:
        raise ValueError("inverse does not exist")
    return x % m


def crt(remainders, moduli):
    # solve x = r_i (mod m_i); moduli must be pairwise coprime
    big_m = 1
    for m in moduli:
        big_m *= m
    x = 0
    for r, m in zip(remainders, moduli):
        part = big_m // m
        inv = mod_inverse(part, m)
        x = (x + r * part * inv) % big_m
    return x


if __name__ == "__main__":
    remainders = [2, 3, 2]
    moduli = [3, 5, 7]
    x = crt(remainders, moduli)
    print("classic system: x = 2 (mod 3), x = 3 (mod 5), x = 2 (mod 7)")
    print("smallest solution:", x)  # 23 (all solutions: 23 + 105k)
    print("checks:", [x % m for m in moduli])
    print("another system:", crt([1, 4, 0], [5, 9, 7]))
