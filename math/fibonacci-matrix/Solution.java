// Stepsort · Fibonacci (Matrix Exp)
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-matrix

public class Main {
    static long[][] mult(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    static long[][] matPower(long[][] m, long p) {
        long[][] result = {{1, 0}, {0, 1}};
        while (p > 0) {
            if ((p & 1) == 1) result = mult(result, m);
            m = mult(m, m);
            p >>= 1;
        }
        return result;
    }

    static long fib(long n) {
        // F(n) is an off-diagonal entry of [[1, 1], [1, 0]]^n; F(0) = 0
        if (n == 0) return 0;
        long[][] base = {{1, 1}, {1, 0}};
        return matPower(base, n)[0][1];
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("F(0..10):");
        for (int i = 0; i <= 10; ++i) sb.append(" ").append(fib(i));
        System.out.println(sb.toString());
        System.out.println("F(50) = " + fib(50));
        System.out.println("F(90) = " + fib(90));
    }
}
