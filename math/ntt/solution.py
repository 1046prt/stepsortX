# sortsort · Number Theoretic Transform
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ntt

MOD = 998244353
ROOT = 3


def power(base, exp):
    # modular exponentiation
    result = 1
    base %= MOD
    while exp > 0:
        if exp & 1:
            result = result * base % MOD
        base = base * base % MOD
        exp >>= 1
    return result


def ntt(a, invert):
    # iterative Cooley-Tukey transform, len(a) must be a power of two
    n = len(a)
    j = 0
    for i in range(1, n):  # bit-reversal permutation
        bit = n >> 1
        while j & bit:
            j ^= bit
            bit >>= 1
        j ^= bit
        if i < j:
            a[i], a[j] = a[j], a[i]
    length = 2
    while length <= n:
        w_len = power(ROOT, (MOD - 1) // length)
        if invert:
            w_len = power(w_len, MOD - 2)
        half = length >> 1
        for start in range(0, n, length):
            w = 1
            for k in range(half):
                u = a[start + k]
                v = a[start + k + half] * w % MOD
                a[start + k] = (u + v) % MOD
                a[start + k + half] = (u - v) % MOD
                w = w * w_len % MOD
        length <<= 1
    if invert:
        n_inv = power(n, MOD - 2)
        for i in range(n):
            a[i] = a[i] * n_inv % MOD


def multiply(poly_a, poly_b):
    result_size = len(poly_a) + len(poly_b) - 1
    size = 1
    while size < result_size:
        size <<= 1
    fa = list(poly_a) + [0] * (size - len(poly_a))
    fb = list(poly_b) + [0] * (size - len(poly_b))
    ntt(fa, False)
    ntt(fb, False)
    fa = [x * y % MOD for x, y in zip(fa, fb)]
    ntt(fa, True)
    return fa[:result_size]


if __name__ == "__main__":
    # (1 + 2x + 3x^2) * (4 + 5x + 6x^2) = 4 + 13x + 28x^2 + 27x^3 + 18x^4
    product = multiply([1, 2, 3], [4, 5, 6])
    print("product coefficients:", product)
