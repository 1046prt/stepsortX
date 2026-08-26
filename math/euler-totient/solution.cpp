// sortsort · Euler's Totient
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/euler-totient

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

// phi(n) = n * prod(1 - 1/p) over distinct primes p dividing n
ll phi_factorization(ll n) {
    ll result = n;
    for (ll p = 2; p * p <= n; ++p) {
        if (n % p == 0) {
            while (n % p == 0) n /= p;
            result -= result / p;
        }
    }
    if (n > 1) result -= result / n;
    return result;
}

// phi(i) for every i from 0 to limit in O(n log log n)
vector<ll> phi_sieve(int limit) {
    vector<ll> phi(limit + 1);
    for (int i = 0; i <= limit; ++i) phi[i] = i;
    for (int i = 2; i <= limit; ++i) {
        if (phi[i] == i) {  // untouched means no smaller factor exists: prime
            for (int j = i; j <= limit; j += i) {
                phi[j] -= phi[j] / i;
            }
        }
    }
    return phi;
}

int main() {
    ll tests[] = {1, 12, 36, 97, 100};
    for (ll n : tests) {
        cout << "phi(" << n << ") = " << phi_factorization(n) << endl;
    }
    vector<ll> table = phi_sieve(20);
    cout << "sieve 1..20:";
    for (size_t i = 1; i < table.size(); ++i) cout << " " << table[i];
    cout << endl;
    return 0;
}
