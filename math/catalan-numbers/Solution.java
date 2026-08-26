// sortsort · Catalan Numbers
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/catalan-numbers

public class Main {

    static long[] catalanDp(int count) {
        long[] cat = new long[count];
        cat[0] = 1;
        for (int i = 1; i < count; ++i) {
            for (int j = 0; j < i; ++j) {
                cat[i] += cat[j] * cat[i - 1 - j];
            }
        }
        return cat;
    }

    static long binomial(int n, int k) {
        long result = 1;
        for (int i = 0; i < k; ++i) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }

    static long catalanClosedForm(int n) {
        // nth Catalan number = C(2n, n) / (n + 1)
        return binomial(2 * n, n) / (n + 1);
    }

    public static void main(String[] args) {
        long[] cat = catalanDp(10);
        System.out.print("First 10 Catalan numbers:");
        for (long c : cat) System.out.print(" " + c);
        System.out.println();
        boolean ok = true;
        for (int i = 0; i < 10; ++i) {
            if (catalanClosedForm(i) != cat[i]) ok = false;
        }
        System.out.println("closed form matches: " + ok);
    }
}
