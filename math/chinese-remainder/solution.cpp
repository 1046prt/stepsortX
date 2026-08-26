// sortsort · Chinese Remainder Theorem
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/chinese-remainder

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

// returns g = gcd(a, b) and sets x, y with a*x + b*y = g
ll extended_gcd(ll a, ll b, ll& x, ll& y) {
    if (b == 0) {
        x = 1;
        y = 0;
        return a;
    }
    ll x1, y1;
    ll g = extended_gcd(b, a % b, x1, y1);
    x = y1;
    y = x1 - (a / b) * y1;
    return g;
}

ll mod_inverse(ll a, ll m) {
    ll x, y;
    ll g = extended_gcd(((a % m) + m) % m, m, x, y);
    if (g != 1) throw runtime_error("inverse does not exist");
    x %= m;
    if (x < 0) x += m;
    return x;
}

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

// moduli must be pairwise coprime; their product must fit in a long long
ll crt(const vector<ll>& rem, const vector<ll>& mod) {
    ll big_m = 1;
    for (ll m : mod) big_m *= m;
    ll x = 0;
    for (size_t i = 0; i < mod.size(); ++i) {
        ll part = big_m / mod[i];
        ll inv = mod_inverse(part, mod[i]);
        ll term = mulmod(mulmod(rem[i] % big_m, part, big_m), inv, big_m);
        x = addmod(x, term, big_m);
    }
    return x;
}

int main() {
    vector<ll> rem = {2, 3, 2};
    vector<ll> mod = {3, 5, 7};
    ll x = crt(rem, mod);
    cout << "classic system: x = 2 (mod 3), x = 3 (mod 5), x = 2 (mod 7)" << endl;
    cout << "smallest solution: " << x << " (mod 105)" << endl;
    cout << "checks:";
    for (size_t i = 0; i < mod.size(); ++i) cout << " " << x % mod[i];
    cout << endl;
    cout << "another system: "
         << crt(vector<ll>{1, 4, 0}, vector<ll>{5, 9, 7}) << endl;
    return 0;
}
