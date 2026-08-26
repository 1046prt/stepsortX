// sortsort · Number Theoretic Transform
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ntt

import java.util.Arrays;

public class Main {
    static final long MOD = 998244353L;
    static final long ROOT = 3L;

    static long power(long base, long exp) {
        // modular exponentiation
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    static void ntt(long[] a, boolean invert) {
        // iterative Cooley-Tukey transform, a.length must be a power of two
        int n = a.length;
        for (int i = 1, j = 0; i < n; ++i) {  // bit-reversal permutation
            int bit = n >> 1;
            for (; (j & bit) != 0; bit >>= 1) j ^= bit;
            j ^= bit;
            if (i < j) {
                long t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        for (int length = 2; length <= n; length <<= 1) {
            long wLen = power(ROOT, (MOD - 1) / length);
            if (invert) wLen = power(wLen, MOD - 2);
            int half = length >> 1;
            for (int start = 0; start < n; start += length) {
                long w = 1;
                for (int k = 0; k < half; ++k) {
                    long u = a[start + k];
                    long v = a[start + k + half] * w % MOD;
                    a[start + k] = (u + v) % MOD;
                    a[start + k + half] = (u - v + MOD) % MOD;
                    w = w * wLen % MOD;
                }
            }
        }
        if (invert) {
            long nInv = power(n, MOD - 2);
            for (int i = 0; i < n; ++i) a[i] = a[i] * nInv % MOD;
        }
    }

    static long[] multiply(long[] a, long[] b) {
        int resultSize = a.length + b.length - 1;
        int size = 1;
        while (size < resultSize) size <<= 1;
        long[] fa = new long[size];
        long[] fb = new long[size];
        System.arraycopy(a, 0, fa, 0, a.length);
        System.arraycopy(b, 0, fb, 0, b.length);
        ntt(fa, false);
        ntt(fb, false);
        for (int i = 0; i < size; ++i) fa[i] = fa[i] * fb[i] % MOD;
        ntt(fa, true);
        return Arrays.copyOf(fa, resultSize);
    }

    public static void main(String[] args) {
        // (1 + 2x + 3x^2) * (4 + 5x + 6x^2) = 4 + 13x + 28x^2 + 27x^3 + 18x^4
        long[] pa = {1, 2, 3};
        long[] pb = {4, 5, 6};
        long[] product = multiply(pa, pb);
        StringBuilder sb = new StringBuilder("product coefficients:");
        for (long c : product) sb.append(" ").append(c);
        System.out.println(sb.toString());
    }
}
