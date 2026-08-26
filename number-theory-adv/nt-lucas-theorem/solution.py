# sortsort · Lucas' Theorem
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-lucas-theorem

def init_binomials(p):
    # factorial tables modulo prime p, indices run over residues 0..p-1
    fact = [1] * p
    for i in range(1, p):
        fact[i] = fact[i - 1] * i % p
    inv_fact = [1] * p
    inv_fact[p - 1] = pow(fact[p - 1], p - 2, p)
    for i in range(p - 1, 0, -1):
        inv_fact[i - 1] = inv_fact[i] * i % p
    return fact, inv_fact


def binomial_small(n, k, fact, inv_fact, p):
    if k < 0 or k > n:
        return 0
    return fact[n] * inv_fact[k] % p * inv_fact[n - k] % p


def lucas(n, k, p, fact, inv_fact):
    # C(n, k) = product of C(n_i, k_i) over base-p digits
    res = 1
    while n > 0 or k > 0:
        res = res * binomial_small(n % p, k % p, fact, inv_fact, p) % p
        n //= p
        k //= p
    return res


if __name__ == "__main__":
    p = 7
    fact, inv_fact = init_binomials(p)
    print("C(10,3) mod", p, "=", lucas(10, 3, p, fact, inv_fact))
    print("C(14,6) mod", p, "=", lucas(14, 6, p, fact, inv_fact))
    print("C(50,20) mod", p, "=", lucas(50, 20, p, fact, inv_fact))

    p = 13
    fact, inv_fact = init_binomials(p)
    print("C(100,30) mod", p, "=", lucas(100, 30, p, fact, inv_fact))
    print("C(1000,500) mod", p, "=", lucas(1000, 500, p, fact, inv_fact))
