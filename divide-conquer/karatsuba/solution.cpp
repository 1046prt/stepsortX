// Stepsort · Karatsuba Multiplication
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/karatsuba

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

ll powerOfTen(int exponent) {
    ll result = 1;
    for (int i = 0; i < exponent; i++) result *= 10;
    return result;
}

// multiply two non-negative integers by splitting around 10^half
ll karatsuba(ll x, ll y) {
    if (x < 10 || y < 10) return x * y;
    int digits = max((int)to_string(x).size(), (int)to_string(y).size());
    int half = digits / 2;
    ll power = powerOfTen(half);
    ll xHigh = x / power, xLow = x % power;
    ll yHigh = y / power, yLow = y % power;
    ll z0 = karatsuba(xLow, yLow);
    ll z2 = karatsuba(xHigh, yHigh);
    ll z1 = karatsuba(xHigh + xLow, yHigh + yLow) - z2 - z0;
    return z2 * powerOfTen(2 * half) + z1 * power + z0;
}

int main() {
    pair<ll, ll> samples[] = {
        {123456789LL, 987654321LL},
        {2147483647LL, 3037000499LL},
    };
    for (const auto& s : samples)
        cout << s.first << " * " << s.second << " = "
             << karatsuba(s.first, s.second) << endl;
    return 0;
}
