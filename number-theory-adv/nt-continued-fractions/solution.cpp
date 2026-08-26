// sortsort · Continued Fractions
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-continued-fractions

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

ll floordiv(ll a, ll b) {
    // floor division, keeps every partial quotient well defined
    ll d = a / b;
    if (a % b != 0 && ((a < 0) != (b < 0))) d--;
    return d;
}

// expand p/q into [a0; a1, a2, ...] with p/q = a0 + 1/(a1 + 1/(a2 + ...))
vector<ll> continued_fraction(ll p, ll q) {
    vector<ll> terms;
    if (q < 0) {
        p = -p;
        q = -q;
    }
    while (q != 0) {
        ll a = floordiv(p, q);
        terms.push_back(a);
        ll r = p - a * q;
        p = q;
        q = r;
    }
    return terms;
}

void show(ll p, ll q) {
    vector<ll> t = continued_fraction(p, q);
    cout << p << "/" << q << " -> [" << t[0];
    for (size_t i = 1; i < t.size(); i++)
        cout << (i == 1 ? "; " : ", ") << t[i];
    cout << "]" << endl;
}

int main() {
    show(43, 19);
    show(649, 200);
    show(5, 3);
    show(13, 8);
    show(7, 1);
    show(-43, 19);
    return 0;
}
