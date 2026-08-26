// Stepsort · Lucas' Theorem
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-lucas-theorem

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

ll MOD;
vector<ll> fact, inv_fact;

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

// factorial tables modulo prime p
void init_binomials(ll p) {
    MOD = p;
    fact.assign(p, 1);
    for (ll i = 1; i < p; i++) fact[i] = fact[i - 1] * i % p;
    inv_fact.assign(p, 1);
    inv_fact[p - 1] = pow_mod(fact[p - 1], p - 2, p);
    for (ll i = p - 1; i > 0; i--) inv_fact[i - 1] = inv_fact[i] * i % p;
}

ll binomial_small(ll n, ll k) {
    if (k < 0 || k > n) return 0;
    return fact[n] * inv_fact[k] % MOD * inv_fact[n - k] % MOD;
}

// C(n, k) = product of C(n_i, k_i) over base-p digits
ll lucas(ll n, ll k) {
    ll res = 1;
    while (n > 0 || k > 0) {
        res = res * binomial_small(n % MOD, k % MOD) % MOD;
        n /= MOD;
        k /= MOD;
    }
    return res;
}

int main() {
    init_binomials(7);
    cout << "C(10,3) mod 7 = " << lucas(10, 3) << endl;
    cout << "C(14,6) mod 7 = " << lucas(14, 6) << endl;
    cout << "C(50,20) mod 7 = " << lucas(50, 20) << endl;

    init_binomials(13);
    cout << "C(100,30) mod 13 = " << lucas(100, 30) << endl;
    cout << "C(1000,500) mod 13 = " << lucas(1000, 500) << endl;
    return 0;
}
