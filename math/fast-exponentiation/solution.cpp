// sortsort · Fast Exponentiation
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fast-exponentiation

#include <bits/stdc++.h>
using namespace std;

// Keep exponents small in plain power(): results overflow quickly.
long long power(long long a, long long b) {
    long long result = 1;
    while (b > 0) {
        if (b & 1) result *= a;
        a *= a;
        b >>= 1;
    }
    return result;
}

long long powMod(long long a, long long b, long long m) {
    long long result = 1;
    a %= m;
    while (b > 0) {
        if (b & 1) result = result * a % m;
        a = a * a % m;
        b >>= 1;
    }
    return result;
}

int main() {
    cout << "2^10 = " << power(2, 10) << endl;
    cout << "3^13 = " << power(3, 13) << endl;
    cout << "5^20 = " << power(5, 20) << endl;
    cout << "2^100 mod 1000000007 = "
         << powMod(2, 100, 1000000007LL) << endl;
    cout << "123456789^987654321 mod 1000000007 = "
         << powMod(123456789LL, 987654321LL, 1000000007LL) << endl;
    return 0;
}
