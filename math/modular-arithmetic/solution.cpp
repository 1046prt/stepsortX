// Stepsort · Modular Arithmetic
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/modular-arithmetic

#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007LL;

long long modAdd(long long a, long long b) {
    return (a % MOD + b % MOD) % MOD;
}

long long modSub(long long a, long long b) {
    return ((a - b) % MOD + MOD) % MOD;  // fix possible negative remainder
}

long long modMul(long long a, long long b) {
    return a % MOD * (b % MOD) % MOD;
}

long long modPow(long long a, long long b) {
    long long result = 1;
    a %= MOD;
    while (b > 0) {
        if (b & 1) result = result * a % MOD;
        a = a * a % MOD;
        b >>= 1;
    }
    return result;
}

long long modInverse(long long a) {
    // Fermat's little theorem: valid because MOD is prime.
    return modPow(a, MOD - 2);
}

int main() {
    cout << "add(1000000006, 2) = " << modAdd(1000000006LL, 2) << endl;
    cout << "sub(3, 5) = " << modSub(3, 5) << endl;
    cout << "mul(123456789, 987654321) = "
         << modMul(123456789LL, 987654321LL) << endl;
    cout << "inverse of 2 = " << modInverse(2) << endl;
    cout << "check inverse(7) * 7 mod M = "
         << modMul(modInverse(7), 7) << endl;
    return 0;
}
