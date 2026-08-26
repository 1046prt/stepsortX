# Stepsort · FFT (Fast Fourier Transform)
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fft

import cmath


def fft(a, invert=False):
    # iterative radix-2 Cooley-Tukey transform, done in place
    n = len(a)
    j = 0
    for i in range(1, n):
        bit = n >> 1
        while j & bit:
            j ^= bit
            bit >>= 1
        j |= bit
        if i < j:
            a[i], a[j] = a[j], a[i]
    length = 2
    while length <= n:
        sign = 1 if invert else -1
        w_len = complex(cmath.cos(sign * 2 * cmath.pi / length),
                        cmath.sin(sign * 2 * cmath.pi / length))
        for start in range(0, n, length):
            w = 1
            for k in range(length // 2):
                u = a[start + k]
                v = a[start + k + length // 2] * w
                a[start + k] = u + v
                a[start + k + length // 2] = u - v
                w *= w_len
        length <<= 1
    if invert:
        for i in range(n):
            a[i] /= n


def multiply_poly(p, q):
    size = 1
    while size < len(p) + len(q) - 1:
        size <<= 1
    fa = [complex(v) for v in p] + [0j] * (size - len(p))
    fb = [complex(v) for v in q] + [0j] * (size - len(q))
    fft(fa)
    fft(fb)
    fa = [u * v for u, v in zip(fa, fb)]
    fft(fa, invert=True)
    return [round(z.real) for z in fa[:len(p) + len(q) - 1]]


if __name__ == "__main__":
    p = [1, 2, 3]
    q = [4, 5]
    print("p coefficients:", p)
    print("q coefficients:", q)
    print("product coefficients:", multiply_poly(p, q))
