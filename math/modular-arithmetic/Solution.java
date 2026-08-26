// Stepsort · Modular Arithmetic
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/modular-arithmetic

public class Main {

    static final long MOD = 1000000007L;

    static long modAdd(long a, long b) {
        return (a % MOD + b % MOD) % MOD;
    }

    static long modSub(long a, long b) {
        return ((a - b) % MOD + MOD) % MOD;
    }

    static long modMul(long a, long b) {
        return a % MOD * (b % MOD) % MOD;
    }

    static long modPow(long a, long b) {
        long result = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) result = result * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return result;
    }

    static long modInverse(long a) {
        return modPow(a, MOD - 2);  // Fermat, MOD is prime
    }

    public static void main(String[] args) {
        System.out.println("add(1000000006, 2) = " + modAdd(1000000006L, 2));
        System.out.println("sub(3, 5) = " + modSub(3, 5));
        System.out.println("mul(123456789, 987654321) = "
            + modMul(123456789L, 987654321L));
        System.out.println("inverse of 2 = " + modInverse(2));
        System.out.println("check inverse(7) * 7 mod M = "
            + modMul(modInverse(7), 7));
    }
}
