// sortsort · Primitive Root
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-primitive-root

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

vector<ll> prime_factors(ll m) {
    vector<ll> fs;
    for (ll d = 2; d * d <= m; d++) {
        if (m % d == 0) {
            fs.push_back(d);
            while (m % d == 0) m /= d;
        }
    }
    if (m > 1) fs.push_back(m);
    return fs;
}

// g generates iff g^((p-1)/q) != 1 for every prime q dividing p-1
ll primitive_root(ll p) {
    if (p == 2) return 1;
    ll phi = p - 1;
    vector<ll> fs = prime_factors(phi);
    for (ll g = 2; g < p; g++) {
        bool ok = true;
        for (ll q : fs) {
            if (pow_mod(g, phi / q, p) == 1) {
                ok = false;
                break;
            }
        }
        if (ok) return g;
    }
    return -1;
}

int main() {
    for (ll p : {2LL, 3LL, 7LL, 13LL, 31LL, 97LL}) {
        ll g = primitive_root(p);
        cout << "smallest primitive root mod " << p << " = " << g
             << " | verify g^(p-1) mod p = " << pow_mod(g, p - 1, p) << endl;
    }
    return 0;
}
