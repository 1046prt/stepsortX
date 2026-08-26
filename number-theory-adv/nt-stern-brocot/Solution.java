// sortsort · Stern-Brocot Tree
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-stern-brocot

public class Main {
    static long gcd(long a, long b) {
        while (b != 0) { long t = a % b; a = b; b = t; }
        return a;
    }

    // L/R path from the root 1/1 down to num/den (positive fraction)
    static String sternBrocotPath(long num, long den) {
        long g = gcd(num, den);
        num /= g;
        den /= g;
        long la = 0, lb = 1, ra = 1, rb = 0;   // bounds are 0/1 and 1/0
        StringBuilder path = new StringBuilder();
        // compare with the mediant using cross products, no floats needed
        while (num * (lb + rb) != den * (la + ra)) {
            if (num * (lb + rb) > den * (la + ra)) {
                path.append('R');
                la += ra;
                lb += rb;
            } else {
                path.append('L');
                ra += la;
                rb += lb;
            }
        }
        return path.toString();
    }

    public static void main(String[] args) {
        long[][] fractions = {{1, 1}, {5, 7}, {7, 5}, {3, 8}, {13, 4}};
        for (long[] f : fractions) {
            String path = sternBrocotPath(f[0], f[1]);
            String shown = path.isEmpty() ? "(already at root 1/1)" : path;
            System.out.println(f[0] + "/" + f[1] + " -> " + shown);
        }
    }
}
