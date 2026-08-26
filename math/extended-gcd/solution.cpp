// sortsort · Extended Euclidean GCD
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/extended-gcd

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

int main() {
    ll pairs[][2] = {{240, 46}, {30, 20}, {17, 5}, {998244353, 1000000000}};
    for (auto& p : pairs) {
        ll a = p[0], b = p[1];
        ll x, y;
        ll g = extended_gcd(a, b, x, y);
        cout << a << "*(" << x << ") + " << b << "*(" << y << ") = " << g << endl;
    }
    return 0;
}
