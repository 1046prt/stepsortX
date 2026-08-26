// Stepsort · Extended Euclidean GCD
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/extended-gcd

public class Main {
    // returns {g, x, y} with a*x + b*y = g = gcd(a, b)
    static long[] extendedGcd(long a, long b) {
        if (b == 0) {
            return new long[]{a, 1, 0};
        }
        long[] sub = extendedGcd(b, a % b);
        return new long[]{sub[0], sub[2], sub[1] - (a / b) * sub[2]};
    }

    public static void main(String[] args) {
        long[][] pairs =
            {{240, 46}, {30, 20}, {17, 5}, {998244353L, 1000000000L}};
        for (long[] p : pairs) {
            long[] r = extendedGcd(p[0], p[1]);
            System.out.println(p[0] + "*(" + r[1] + ") + "
                    + p[1] + "*(" + r[2] + ") = " + r[0]);
        }
    }
}
