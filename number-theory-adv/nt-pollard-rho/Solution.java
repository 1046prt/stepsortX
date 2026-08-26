// Stepsort · Pollard's Rho
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-pollard-rho

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    static final long[] BASES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
    static Random rng = new Random(123456789L);

    // a*b mod m by shift-and-add, safe for moduli below 2^62
    static long mulmod(long a, long b, long m) {
        long res = 0;
        a %= m;
        while (b > 0) {
            if ((b & 1) == 1) {
                res += a;
                if (res >= m) res -= m;
            }
            a <<= 1;
            if (a >= m) a -= m;
            b >>= 1;
        }
        return res;
    }

    static long powmod(long a, long e, long m) {
        long r = 1;
        a %= m;
        while (e > 0) {
            if ((e & 1) == 1) r = mulmod(r, a, m);
            a = mulmod(a, a, m);
            e >>= 1;
        }
        return r;
    }

    static long gcd(long a, long b) {
        while (b != 0) { long t = a % b; a = b; b = t; }
        return a;
    }

    // deterministic Miller-Rabin, valid for all 64-bit values
    static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long p : BASES) if (n % p == 0) return n == p;
        int s = 0;
        long d = n - 1;
        while (d % 2 == 0) { d /= 2; s++; }
        for (long a : BASES) {
            long x = powmod(a, d, n);
            if (x == 1 || x == n - 1) continue;
            boolean composite = true;
            for (int i = 0; i < s - 1; i++) {
                x = mulmod(x, x, n);
                if (x == n - 1) { composite = false; break; }
            }
            if (composite) return false;
        }
        return true;
    }

    // Floyd cycle detection on f(x) = x*x + c (mod n)
    static long pollardRho(long n) {
        if (n % 2 == 0) return 2;
        while (true) {
            long c = 1 + (rng.nextLong() & Long.MAX_VALUE) % (n - 1);
            long x = (rng.nextLong() & Long.MAX_VALUE) % n;
            long y = x, d = 1;
            while (d == 1) {
                x = (mulmod(x, x, n) + c) % n;
                y = (mulmod(y, y, n) + c) % n;
                y = (mulmod(y, y, n) + c) % n;
                long diff = x - y;
                if (diff < 0) diff = -diff;
                d = gcd(diff, n);
            }
            if (d != n) return d;
        }
    }

    static void factor(long n, List<Long> out) {
        if (n == 1) return;
        if (isPrime(n)) { out.add(n); return; }
        long d = pollardRho(n);
        factor(d, out);
        factor(n / d, out);
    }

    public static void main(String[] args) {
        long[] tests = {91, 8051, 10403, 9973L * 10007L};
        for (long n : tests) {
            List<Long> fs = new ArrayList<>();
            factor(n, fs);
            Collections.sort(fs);
            StringBuilder sb = new StringBuilder();
            sb.append(n).append(" = ");
            for (int i = 0; i < fs.size(); i++) {
                if (i > 0) sb.append(" * ");
                sb.append(fs.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
