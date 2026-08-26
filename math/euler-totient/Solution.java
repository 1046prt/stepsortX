// Stepsort · Euler's Totient
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/euler-totient

public class Main {
    // phi(n) = n * prod(1 - 1/p) over distinct primes p dividing n
    static long phiFactorization(long n) {
        long result = n;
        for (long p = 2; p * p <= n; ++p) {
            if (n % p == 0) {
                while (n % p == 0) n /= p;
                result -= result / p;
            }
        }
        if (n > 1) result -= result / n;
        return result;
    }

    // phi(i) for every i from 0 to limit in O(n log log n)
    static long[] phiSieve(int limit) {
        long[] phi = new long[limit + 1];
        for (int i = 0; i <= limit; ++i) phi[i] = i;
        for (int i = 2; i <= limit; ++i) {
            if (phi[i] == i) {  // untouched means no smaller factor exists: prime
                for (int j = i; j <= limit; j += i) {
                    phi[j] -= phi[j] / i;
                }
            }
        }
        return phi;
    }

    public static void main(String[] args) {
        long[] tests = {1, 12, 36, 97, 100};
        for (long n : tests) {
            System.out.println("phi(" + n + ") = " + phiFactorization(n));
        }
        long[] table = phiSieve(20);
        StringBuilder sb = new StringBuilder("sieve 1..20:");
        for (int i = 1; i < table.length; ++i) sb.append(" ").append(table[i]);
        System.out.println(sb.toString());
    }
}
