// sortsort · Möbius Function
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-mobius-function

#include <bits/stdc++.h>
using namespace std;

vector<int> spf, mu;

void build_mobius(int n) {
    // smallest-prime-factor sieve
    spf.resize(n + 1);
    iota(spf.begin(), spf.end(), 0);
    for (int i = 2; (long long)i * i <= n; i++)
        if (spf[i] == i)
            for (int j = i * i; j <= n; j += i)
                if (spf[j] == j) spf[j] = i;
    mu.assign(n + 1, 0);
    mu[1] = 1;
    // strip smallest prime p from i = p*rest; p | rest means p^2 | i
    for (int i = 2; i <= n; i++) {
        int p = spf[i];
        int rest = i / p;
        mu[i] = (rest % p == 0) ? 0 : -mu[rest];
    }
}

int main() {
    int n = 20;
    build_mobius(n);
    cout << "n : ";
    for (int i = 1; i <= n; i++) cout << setw(3) << i;
    cout << endl << "mu: ";
    for (int i = 1; i <= n; i++) cout << setw(3) << mu[i];
    cout << endl;
    long long mertens = 0;
    for (int i = 1; i <= n; i++) mertens += mu[i];
    cout << "Mertens M(" << n << ") = " << mertens << endl;
    return 0;
}
