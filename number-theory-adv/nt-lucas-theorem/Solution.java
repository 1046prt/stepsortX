// sortsort · Lucas' Theorem
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-lucas-theorem

public class Main {
    static long MOD;
    static long[] fact, invFact;

    static long powmod(long a, long e, long m) {
        long r = 1;
        a %= m;
        while (e > 0) {
            if ((e & 1) == 1) r = r * a % m;
            a = a * a % m;
            e >>= 1;
        }
        return r;
    }

    // factorial tables modulo prime p
    static void initBinomials(long p) {
        MOD = p;
        fact = new long[(int) p];
        invFact = new long[(int) p];
        fact[0] = 1;
        for (int i = 1; i < p; i++) fact[i] = fact[i - 1] * i % p;
        invFact[(int) p - 1] = powmod(fact[(int) p - 1], p - 2, p);
        for (int i = (int) p - 1; i > 0; i--) invFact[i - 1] = invFact[i] * i % p;
    }

    static long binomialSmall(long n, long k) {
        if (k < 0 || k > n) return 0;
        return fact[(int) n] * invFact[(int) k] % MOD * invFact[(int) (n - k)] % MOD;
    }

    // C(n, k) = product of C(n_i, k_i) over base-p digits
    static long lucas(long n, long k) {
        long res = 1;
        while (n > 0 || k > 0) {
            res = res * binomialSmall(n % MOD, k % MOD) % MOD;
            n /= MOD;
            k /= MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        initBinomials(7);
        System.out.println("C(10,3) mod 7 = " + lucas(10, 3));
        System.out.println("C(14,6) mod 7 = " + lucas(14, 6));
        System.out.println("C(50,20) mod 7 = " + lucas(50, 20));

        initBinomials(13);
        System.out.println("C(100,30) mod 13 = " + lucas(100, 30));
        System.out.println("C(1000,500) mod 13 = " + lucas(1000, 500));
    }
}
