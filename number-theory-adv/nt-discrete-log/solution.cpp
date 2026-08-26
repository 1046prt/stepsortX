// sortsort · Discrete Logarithm
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-discrete-log

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

// least x >= 0 with g^x = h (mod p); assumes p prime and gcd(g, p) = 1
ll bsgs(ll g, ll h, ll p) {
    ll m = 1;
    while (m * m < p) m++;
    unordered_map<ll, ll> baby;
    ll cur = 1;
    for (ll j = 0; j < m; j++) {
        if (!baby.count(cur)) baby[cur] = j;
        cur = cur * g % p;
    }
    ll step = pow_mod(pow_mod(g, m, p), p - 2, p);  // g^-m via Fermat
    ll gamma = ((h % p) + p) % p;
    for (ll i = 0; i <= m; i++) {
        auto it = baby.find(gamma);
        if (it != baby.end()) return i * m + it->second;
        gamma = gamma * step % p;
    }
    return -1;
}

int main() {
    vector<array<ll, 3>> cases = {{3, 13, 17}, {5, 3, 23}, {6, 5, 41}};
    for (auto& c : cases) {
        ll g = c[0], h = c[1], p = c[2];
        ll x = bsgs(g, h, p);
        bool ok = x >= 0 && pow_mod(g, x, p) == ((h % p) + p) % p;
        cout << "log base " << g << " of " << h << " mod " << p << " = x = "
             << x << " | " << (ok ? "verified" : "not found") << endl;
    }
    return 0;
}
