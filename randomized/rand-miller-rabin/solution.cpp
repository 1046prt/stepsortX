// Stepsort · Miller-Rabin (Randomized)
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-miller-rabin

#include <bits/stdc++.h>
using namespace std;

mt19937 rng(42);

long long mulmod(long long a, long long b, long long mod) {
    return static_cast<__int128>(a) * b % mod;
}

long long powmod(long long base, long long exponent, long long mod) {
    long long result = 1;
    base %= mod;
    while (exponent > 0) {
        if (exponent & 1) result = mulmod(result, base, mod);
        base = mulmod(base, base, mod);
        exponent >>= 1;
    }
    return result;
}

// Randomized Miller-Rabin: random bases, error probability <= 4^-rounds.
bool millerRabin(long long n, int rounds = 8) {
    if (n < 2) return false;
    for (long long p : {2LL, 3LL, 5LL, 7LL, 11LL, 13LL}) {
        if (n % p == 0) return n == p;
    }
    long long d = n - 1;
    int r = 0;
    while (d % 2 == 0) {
        d /= 2;
        r++;
    }
    uniform_int_distribution<long long> base(2, n - 2);
    for (int round = 0; round < rounds; round++) {
        long long a = base(rng);
        long long x = powmod(a, d, n);
        if (x == 1 || x == n - 1) continue;
        bool composite = true;
        for (int i = 0; i < r - 1; i++) {
            x = mulmod(x, x, n);
            if (x == n - 1) {
                composite = false;
                break;
            }
        }
        if (composite) return false;  // a witnesses compositeness
    }
    return true;
}

int main() {
    long long tests[] = {97, 561, 7919, 1105, 999983};
    for (long long n : tests) {
        string verdict = millerRabin(n) ? "prime" : "composite";
        cout << n << " is " << verdict << endl;
    }
    return 0;
}
