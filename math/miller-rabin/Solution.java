// Stepsort · Miller-Rabin Primality
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/miller-rabin

public class Main {
    static final long[] BASES = {2, 3, 5, 7, 11, 13, 17};

    // (a + b) mod m for a, b in [0, m)
    static long addMod(long a, long b, long m) {
        long t = m - b;
        return a >= t ? a - t : a + b;
    }

    // (a * b) mod m via shift-and-add, avoiding 64-bit overflow
    static long mulMod(long a, long b, long m) {
        long result = 0;
        a %= m;
        while (b > 0) {
            if ((b & 1) == 1) result = addMod(result, a, m);
            a = addMod(a, a, m);
            b >>= 1;
        }
        return result;
    }

    static long powMod(long base, long exp, long m) {
        long result = 1;
        base %= m;
        while (exp > 0) {
            if ((exp & 1) == 1) result = mulMod(result, base, m);
            base = mulMod(base, base, m);
            exp >>= 1;
        }
        return result;
    }

    static boolean isPrime(long n) {
        // deterministic for all n < 341550071728321 with this witness set
        if (n < 2) return false;
        for (long p : BASES) {
            if (n % p == 0) return n == p;
        }
        long d = n - 1;
        int s = 0;
        while ((d & 1) == 0) {
            d >>= 1;
            ++s;
        }
        for (long a : BASES) {
            long x = powMod(a, d, n);
            if (x == 1 || x == n - 1) continue;
            boolean survived = false;
            for (int r = 1; r < s; ++r) {
                x = mulMod(x, x, n);
                if (x == n - 1) {
                    survived = true;
                    break;
                }
            }
            if (!survived) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long[] tests = {1L, 2L, 97L, 561L, 7919L, 3215031751L,
                        2147483647L, 67280421310721L,
                        998244359987710471L, 9223372036854775783L};
        for (long n : tests) {
            String verdict = isPrime(n) ? " is prime" : " is composite";
            System.out.println(n + verdict);
        }
    }
}
