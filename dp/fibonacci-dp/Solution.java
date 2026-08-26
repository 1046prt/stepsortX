// Stepsort · Fibonacci
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-dp

public class Main {
    // Top-down: recursion plus a memo cache (-1 means not computed yet)
    static long fibMemo(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }

    // Bottom-up: iterative table fill
    static long fibTab(int n) {
        if (n <= 1) return n;
        long[] table = new long[n + 1];
        table[1] = 1;
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        return table[n];
    }

    public static void main(String[] args) {
        int n = 10;
        long[] memo = new long[n + 1];
        for (int i = 0; i <= n; i++) memo[i] = -1;
        System.out.println("F(10) top-down memoized: " + fibMemo(n, memo));
        System.out.println("F(10) bottom-up tabulated: " + fibTab(n));
    }
}
