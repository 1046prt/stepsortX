// sortsort · Euclidean GCD
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gcd-euclidean

public class Main {

    static long gcdIterative(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return Math.abs(a);
    }

    static long gcdRecursive(long a, long b) {
        if (b == 0) return Math.abs(a);
        return gcdRecursive(b, a % b);
    }

    static long lcmFromGcd(long a, long b) {
        return a / gcdIterative(a, b) * b;
    }

    public static void main(String[] args) {
        long[][] pairs = {{48, 18}, {100, 75}, {17, 13}, {270, 192}};
        for (long[] p : pairs) {
            System.out.println("gcd(" + p[0] + ", " + p[1] + ") = "
                + gcdIterative(p[0], p[1])
                + " (recursive: " + gcdRecursive(p[0], p[1])
                + "), lcm = " + lcmFromGcd(p[0], p[1]));
        }
    }
}
