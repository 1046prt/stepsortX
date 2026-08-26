// Stepsort · Miller-Rabin Primality
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/miller-rabin

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

// (a + b) mod m for a, b in [0, m)
ll addmod(ll a, ll b, ll m) {
    ll t = m - b;
    return a >= t ? a - t : a + b;
}

// (a * b) mod m via shift-and-add, safe from 64-bit overflow
ll mulmod(ll a, ll b, ll m) {
    ll result = 0;
    a %= m;
    while (b > 0) {
        if (b & 1) result = addmod(result, a, m);
        a = addmod(a, a, m);
        b >>= 1;
    }
    return result;
}

ll powmod(ll base, ll exp, ll m) {
    ll result = 1;
    base %= m;
    while (exp > 0) {
        if (exp & 1) result = mulmod(result, base, m);
        base = mulmod(base, base, m);
        exp >>= 1;
    }
    return result;
}

bool is_prime(ll n) {
    // deterministic for all n < 341550071728321 with this witness set
    ll bases[] = {2, 3, 5, 7, 11, 13, 17};
    if (n < 2) return false;
    for (ll p : bases) {
        if (n % p == 0) return n == p;
    }
    ll d = n - 1;
    int s = 0;
    while ((d & 1) == 0) {
        d >>= 1;
        ++s;
    }
    for (ll a : bases) {
        ll x = powmod(a, d, n);
        if (x == 1 || x == n - 1) continue;
        bool survived = false;
        for (int r = 1; r < s; ++r) {
            x = mulmod(x, x, n);
            if (x == n - 1) {
                survived = true;
                break;
            }
        }
        if (!survived) return false;
    }
    return true;
}

int main() {
    ll tests[] = {1LL, 2LL, 97LL, 561LL, 7919LL, 3215031751LL,
                  2147483647LL, 67280421310721LL,
                  998244359987710471LL, 9223372036854775783LL};
    for (ll n : tests) {
        cout << n << (is_prime(n) ? " is prime" : " is composite") << endl;
    }
    return 0;
}
