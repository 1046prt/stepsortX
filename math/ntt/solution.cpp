// sortsort · Number Theoretic Transform
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ntt

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

const ll MOD = 998244353;
const ll ROOT = 3;

ll power(ll base, ll exp) {
    // modular exponentiation
    ll result = 1;
    base %= MOD;
    while (exp > 0) {
        if (exp & 1) result = result * base % MOD;
        base = base * base % MOD;
        exp >>= 1;
    }
    return result;
}

void ntt(vector<ll>& a, bool invert) {
    // iterative Cooley-Tukey transform, a.size() must be a power of two
    int n = a.size();
    for (int i = 1, j = 0; i < n; ++i) {  // bit-reversal permutation
        int bit = n >> 1;
        for (; j & bit; bit >>= 1) j ^= bit;
        j ^= bit;
        if (i < j) swap(a[i], a[j]);
    }
    for (int length = 2; length <= n; length <<= 1) {
        ll w_len = power(ROOT, (MOD - 1) / length);
        if (invert) w_len = power(w_len, MOD - 2);
        int half = length >> 1;
        for (int start = 0; start < n; start += length) {
            ll w = 1;
            for (int k = 0; k < half; ++k) {
                ll u = a[start + k];
                ll v = a[start + k + half] * w % MOD;
                a[start + k] = (u + v) % MOD;
                a[start + k + half] = (u - v + MOD) % MOD;
                w = w * w_len % MOD;
            }
        }
    }
    if (invert) {
        ll n_inv = power(n, MOD - 2);
        for (ll& x : a) x = x * n_inv % MOD;
    }
}

vector<ll> multiply(vector<ll> a, vector<ll> b) {
    int result_size = (int)a.size() + (int)b.size() - 1;
    int size = 1;
    while (size < result_size) size <<= 1;
    a.resize(size);
    b.resize(size);
    ntt(a, false);
    ntt(b, false);
    for (int i = 0; i < size; ++i) a[i] = a[i] * b[i] % MOD;
    ntt(a, true);
    a.resize(result_size);
    return a;
}

int main() {
    // (1 + 2x + 3x^2) * (4 + 5x + 6x^2) = 4 + 13x + 28x^2 + 27x^3 + 18x^4
    vector<ll> pa = {1, 2, 3};
    vector<ll> pb = {4, 5, 6};
    vector<ll> product = multiply(pa, pb);
    cout << "product coefficients:";
    for (ll c : product) cout << " " << c;
    cout << endl;
    return 0;
}
