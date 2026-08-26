// sortsort · Quadratic Residue
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-quadratic-residue

public class Main {
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

    // Euler's criterion: a^((p-1)/2) mod p is 0, 1 or p-1
    static int legendre(long a, long p) {
        long r = powmod(((a % p) + p) % p, (p - 1) / 2, p);
        if (r == 0) return 0;
        return r == 1 ? 1 : -1;
    }

    // square root of residue a modulo odd prime p (Tonelli-Shanks)
    static long tonelliShanks(long a, long p) {
        a = ((a % p) + p) % p;
        if (a == 0) return 0;
        if (p % 4 == 3) return powmod(a, (p + 1) / 4, p);
        long q = p - 1;
        int s = 0;
        while (q % 2 == 0) { q /= 2; s++; }
        long z = 2;
        while (legendre(z, p) != -1) z++;
        long m = s;
        long c = powmod(z, q, p);         // c built from a non-residue
        long t = powmod(a, q, p);
        long r = powmod(a, (q + 1) / 2, p);
        while (t != 1) {
            long i = 0;
            long t2 = t;
            while (t2 != 1) {
                t2 = t2 * t2 % p;
                i++;
            }
            long b = powmod(c, 1L << (m - i - 1), p);
            m = i;
            c = b * b % p;
            t = t * c % p;
            r = r * b % p;
        }
        return r;
    }

    public static void main(String[] args) {
        long p = 13;
        long[] tests = {3, 5, 10};
        for (long a : tests) {
            int e = legendre(a, p);
            String kind = e == 0 ? "zero"
                    : (e == 1 ? "a quadratic residue" : "a non-residue");
            System.out.println(a + " mod " + p + " is " + kind);
            if (e == 1) {
                long root = tonelliShanks(a, p);
                boolean ok = root * root % p == a % p;
                System.out.println("  sqrt = " + root + " and " + (p - root)
                        + " | check: " + ok);
            }
        }
        p = 17;
        long root = tonelliShanks(2, p);
        System.out.println("sqrt of 2 mod " + p + " = " + root
                + " | check: " + (root * root % p == 2));
    }
}
