// Stepsort · Pollard's Rho
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-pollard-rho

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

ll mul_mod(ll a, ll b, ll m) {
    // __int128 keeps the intermediate product exact
    return (__int128)a * b % m;
}

ll pow_mod(ll a, ll e, ll m) {
    ll r = 1;
    a %= m;
    while (e > 0) {
        if (e & 1) r = mul_mod(r, a, m);
        a = mul_mod(a, a, m);
        e >>= 1;
    }
    return r;
}

// deterministic Miller-Rabin, valid for all 64-bit integers
bool is_prime(ll n) {
    if (n < 2) return false;
    for (ll p : {2LL, 3LL, 5LL, 7LL, 11LL, 13LL, 17LL, 19LL, 23LL, 29LL, 31LL, 37LL})
        if (n % p == 0) return n == p;
    ll d = n - 1;
    int s = 0;
    while (d % 2 == 0) { d /= 2; s++; }
    for (ll a : {2LL, 3LL, 5LL, 7LL, 11LL, 13LL, 17LL, 19LL, 23LL, 29LL, 31LL, 37LL}) {
        ll x = pow_mod(a, d, n);
        if (x == 1 || x == n - 1) continue;
        bool composite = true;
        for (int i = 0; i < s - 1; i++) {
            x = mul_mod(x, x, n);
            if (x == n - 1) { composite = false; break; }
        }
        if (composite) return false;
    }
    return true;
}

// Floyd cycle detection on f(x) = x*x + c (mod n)
ll pollard_rho(ll n) {
    if (n % 2 == 0) return 2;
    static mt19937_64 rng(123456789ULL);
    while (true) {
        ll c = rng() % (n - 1) + 1;
        ll x = rng() % n;
        ll y = x, d = 1;
        auto f = [&](ll v) { return (mul_mod(v, v, n) + c) % n; };
        while (d == 1) {
            x = f(x);
            y = f(f(y));
            d = __gcd(llabs(x - y), n);
        }
        if (d != n) return d;
    }
}

void factor(ll n, vector<ll>& out) {
    if (n == 1) return;
    if (is_prime(n)) { out.push_back(n); return; }
    ll d = pollard_rho(n);
    factor(d, out);
    factor(n / d, out);
}

int main() {
    for (ll n : {91LL, 8051LL, 10403LL, 9973LL * 10007LL}) {
        vector<ll> fs;
        factor(n, fs);
        sort(fs.begin(), fs.end());
        cout << n << " = ";
        for (size_t i = 0; i < fs.size(); i++)
            cout << (i ? " * " : "") << fs[i];
        cout << endl;
    }
    return 0;
}
