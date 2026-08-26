// sortsort · Discrete Logarithm
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-discrete-log

import java.util.HashMap;
import java.util.Map;

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

    // least x >= 0 with g^x = h (mod p); assumes p prime and gcd(g, p) = 1
    static long bsgs(long g, long h, long p) {
        long m = 1;
        while (m * m < p) m++;
        Map<Long, Long> baby = new HashMap<>();
        long cur = 1;
        for (long j = 0; j < m; j++) {
            if (!baby.containsKey(cur)) baby.put(cur, j);
            cur = cur * g % p;
        }
        long step = powmod(powmod(g, m, p), p - 2, p);  // g^-m via Fermat
        long gamma = ((h % p) + p) % p;
        for (long i = 0; i <= m; i++) {
            Long hit = baby.get(gamma);
            if (hit != null) return i * m + hit;
            gamma = gamma * step % p;
        }
        return -1;
    }

    public static void main(String[] args) {
        long[][] cases = {{3, 13, 17}, {5, 3, 23}, {6, 5, 41}};
        for (long[] c : cases) {
            long g = c[0], h = c[1], p = c[2];
            long x = bsgs(g, h, p);
            boolean ok = x >= 0 && powmod(g, x, p) == ((h % p) + p) % p;
            System.out.println("log base " + g + " of " + h + " mod " + p
                    + " = x = " + x + " | " + (ok ? "verified" : "not found"));
        }
    }
}
