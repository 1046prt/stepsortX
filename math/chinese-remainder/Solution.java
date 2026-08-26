// Stepsort · Chinese Remainder Theorem
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/chinese-remainder

public class Main {
    // returns g = gcd(a, b) and fills xy with {x, y}: a*x + b*y = g
    static long extendedGcd(long a, long b, long[] xy) {
        if (b == 0) {
            xy[0] = 1;
            xy[1] = 0;
            return a;
        }
        long[] inner = new long[2];
        long g = extendedGcd(b, a % b, inner);
        xy[0] = inner[1];
        xy[1] = inner[0] - (a / b) * inner[1];
        return g;
    }

    static long modInverse(long a, long m) {
        long[] xy = new long[2];
        long g = extendedGcd(((a % m) + m) % m, m, xy);
        if (g != 1) throw new ArithmeticException("inverse does not exist");
        long x = xy[0] % m;
        if (x < 0) x += m;
        return x;
    }

    // moduli must be pairwise coprime; their product must fit in a long
    static long crt(long[] rem, long[] mod) {
        long bigM = 1;
        for (long m : mod) bigM *= m;
        long x = 0;
        for (int i = 0; i < mod.length; ++i) {
            long part = bigM / mod[i];
            long inv = modInverse(part, mod[i]);
            long term = (rem[i] % bigM) * (part % bigM) % bigM * inv % bigM;
            x = (x + term) % bigM;
        }
        return x;
    }

    public static void main(String[] args) {
        long[] rem = {2, 3, 2};
        long[] mod = {3, 5, 7};
        long x = crt(rem, mod);
        System.out.println("classic system: x = 2 (mod 3), x = 3 (mod 5), x = 2 (mod 7)");
        System.out.println("smallest solution: " + x + " (mod 105)");
        System.out.print("checks:");
        for (long m : mod) System.out.print(" " + x % m);
        System.out.println();
        System.out.println("another system: "
                + crt(new long[]{1, 4, 0}, new long[]{5, 9, 7}));
    }
}
