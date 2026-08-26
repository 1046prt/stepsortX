// Stepsort · Miller-Rabin (Randomized)
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-miller-rabin

import java.math.BigInteger;

public class Main {
    static java.util.Random rand = new java.util.Random(42);

    // Randomized Miller-Rabin: random bases, error probability <= 4^-rounds.
    static boolean millerRabin(long n, int rounds) {
        if (n < 2) return false;
        for (long p : new long[]{2, 3, 5, 7, 11, 13}) {
            if (n % p == 0) return n == p;
        }
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger minusOne = bigN.subtract(BigInteger.ONE);
        long d = n - 1;
        int r = 0;
        while (d % 2 == 0) {
            d /= 2;
            r++;
        }
        BigInteger bigD = BigInteger.valueOf(d);
        for (int round = 0; round < rounds; round++) {
            long a = 2 + (long) (rand.nextDouble() * (n - 3));  // in [2, n-2]
            BigInteger x = BigInteger.valueOf(a).modPow(bigD, bigN);
            if (x.equals(BigInteger.ONE) || x.equals(minusOne)) continue;
            boolean composite = true;
            for (int i = 0; i < r - 1; i++) {
                x = x.multiply(x).mod(bigN);
                if (x.equals(minusOne)) {
                    composite = false;
                    break;
                }
            }
            if (composite) return false;  // a witnesses compositeness
        }
        return true;
    }

    public static void main(String[] args) {
        long[] tests = {97, 561, 7919, 1105, 999983};
        for (long n : tests) {
            String verdict = millerRabin(n, 8) ? "prime" : "composite";
            System.out.println(n + " is " + verdict);
        }
    }
}
