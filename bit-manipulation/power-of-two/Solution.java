// Stepsort · Power of Two Check
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/power-of-two

public class Main {
    static boolean isPowerOfTwo(long n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        long[] tests = {0, 1, 2, 3, 16, 31, 64, 100, 128};
        for (long v : tests) {
            System.out.println(v + " -> " + isPowerOfTwo(v));
        }
    }
}
