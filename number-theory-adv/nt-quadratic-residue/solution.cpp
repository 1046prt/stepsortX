// sortsort · Quadratic Residue
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-quadratic-residue

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

ll pow_mod(ll a, ll e, ll m) {
    ll r = 1;
    a %= m;
    while (e > 0) {
        if (e & 1) r = r * a % m;
        a = a * a % m;
        e >>= 1;
    }
    return r;
}

// Euler's criterion: a^((p-1)/2) mod p is 0, 1 or p-1
int legendre(ll a, ll p) {
    ll r = pow_mod(((a % p) + p) % p, (p - 1) / 2, p);
    if (r == 0) return 0;
    return (r == 1) ? 1 : -1;
}

// square root of residue a modulo odd prime p (Tonelli-Shanks)
ll tonelli_shanks(ll a, ll p) {
    a = ((a % p) + p) % p;
    if (a == 0) return 0;
    if (p % 4 == 3) return pow_mod(a, (p + 1) / 4, p);
    ll q = p - 1;
    int s = 0;
    while (q % 2 == 0) { q /= 2; s++; }
    ll z = 2;
    while (legendre(z, p) != -1) z++;
    ll m = s;
    ll c = pow_mod(z, q, p);          // c built from a non-residue
    ll t = pow_mod(a, q, p);
    ll r = pow_mod(a, (q + 1) / 2, p);
    while (t != 1) {
        ll i = 0, t2 = t;
        while (t2 != 1) { t2 = t2 * t2 % p; i++; }
        ll b = pow_mod(c, 1LL << (m - i - 1), p);
        m = i;
        c = b * b % p;
        t = t * c % p;
        r = r * b % p;
    }
    return r;
}

int main() {
    ll p = 13;
    for (ll a : {3LL, 5LL, 10LL}) {
        int e = legendre(a, p);
        string kind = e == 0 ? "zero"
                             : (e == 1 ? "a quadratic residue" : "a non-residue");
        cout << a << " mod " << p << " is " << kind << endl;
        if (e == 1) {
            ll rt = tonelli_shanks(a, p);
            cout << "  sqrt = " << rt << " and " << p - rt << " | check "
                 << (rt * rt % p == a % p ? "ok" : "bad") << endl;
        }
    }
    p = 17;
    ll rt = tonelli_shanks(2, p);
    cout << "sqrt of 2 mod " << p << " = " << rt
         << " | check " << (rt * rt % p == 2 ? "ok" : "bad") << endl;
    return 0;
}
