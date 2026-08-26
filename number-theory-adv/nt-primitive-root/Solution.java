// Stepsort · Primitive Root
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-primitive-root

import java.util.ArrayList;
import java.util.List;

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

    static List<Long> primeFactors(long m) {
        List<Long> fs = new ArrayList<>();
        for (long d = 2; d * d <= m; d++) {
            if (m % d == 0) {
                fs.add(d);
                while (m % d == 0) m /= d;
            }
        }
        if (m > 1) fs.add(m);
        return fs;
    }

    // g generates iff g^((p-1)/q) != 1 for every prime q dividing p-1
    static long primitiveRoot(long p) {
        if (p == 2) return 1;
        long phi = p - 1;
        List<Long> fs = primeFactors(phi);
        for (long g = 2; g < p; g++) {
            boolean ok = true;
            for (long q : fs) {
                if (powmod(g, phi / q, p) == 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) return g;
        }
        return -1;
    }

    public static void main(String[] args) {
        long[] primes = {2, 3, 7, 13, 31, 97};
        for (long p : primes) {
            long g = primitiveRoot(p);
            boolean ok = powmod(g, p - 1, p) == 1;
            for (long k = 1; ok && k < p - 1; k++) ok = powmod(g, k, p) != 1;
            System.out.println("smallest primitive root mod " + p + " = " + g
                    + " | order check: " + ok);
        }
    }
}
