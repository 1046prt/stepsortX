// Stepsort · Fast Exponentiation
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fast-exponentiation

public class Main {

    // Keep exponents small in plain power(): results overflow quickly.
    static long power(long a, long b) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) result *= a;
            a *= a;
            b >>= 1;
        }
        return result;
    }

    static long powMod(long a, long b, long m) {
        long result = 1;
        a %= m;
        while (b > 0) {
            if ((b & 1) == 1) result = result * a % m;
            a = a * a % m;
            b >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("2^10 = " + power(2, 10));
        System.out.println("3^13 = " + power(3, 13));
        System.out.println("5^20 = " + power(5, 20));
        System.out.println("2^100 mod 1000000007 = "
            + powMod(2, 100, 1000000007L));
        System.out.println("123456789^987654321 mod 1000000007 = "
            + powMod(123456789L, 987654321L, 1000000007L));
    }
}
