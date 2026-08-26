// Stepsort · Stern-Brocot Tree
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-stern-brocot

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

// L/R path from the root 1/1 down to num/den (positive fraction)
string stern_brocot_path(ll num, ll den) {
    ll g = __gcd(num, den);
    num /= g;
    den /= g;
    ll la = 0, lb = 1, ra = 1, rb = 0;   // bounds are 0/1 and 1/0
    string path;
    // compare with the mediant using cross products, no floats needed
    while (num * (lb + rb) != den * (la + ra)) {
        if (num * (lb + rb) > den * (la + ra)) {
            path.push_back('R');
            la += ra;
            lb += rb;
        } else {
            path.push_back('L');
            ra += la;
            rb += lb;
        }
    }
    return path;
}

int main() {
    vector<pair<ll, ll>> fractions = {{1, 1}, {5, 7}, {7, 5}, {3, 8}, {13, 4}};
    for (auto& f : fractions) {
        string path = stern_brocot_path(f.first, f.second);
        cout << f.first << "/" << f.second << " -> "
             << (path.empty() ? "(already at root 1/1)" : path) << endl;
    }
    return 0;
}
