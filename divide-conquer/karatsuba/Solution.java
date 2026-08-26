// sortsort · Karatsuba Multiplication
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/karatsuba

public class Main {

    // multiply two non-negative integers by splitting around 10^half
    static long karatsuba(long x, long y) {
        if (x < 10 || y < 10) return x * y;
        int digits = Math.max(Long.toString(x).length(), Long.toString(y).length());
        int half = digits / 2;
        long power = 1;
        for (int i = 0; i < half; i++) power *= 10;
        long xHigh = x / power, xLow = x % power;
        long yHigh = y / power, yLow = y % power;
        long z0 = karatsuba(xLow, yLow);
        long z2 = karatsuba(xHigh, yHigh);
        long z1 = karatsuba(xHigh + xLow, yHigh + yLow) - z2 - z0;
        long shift = 1;
        for (int i = 0; i < 2 * half; i++) shift *= 10;
        return z2 * shift + z1 * power + z0;
    }

    public static void main(String[] args) {
        long[][] samples = {
            {123456789L, 987654321L},
            {2147483647L, 3037000499L},
        };
        for (long[] s : samples)
            System.out.println(s[0] + " * " + s[1] + " = " + karatsuba(s[0], s[1]));
    }
}
