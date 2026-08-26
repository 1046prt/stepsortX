// sortsort · Rolling Hash (Polynomial)
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rolling-hash

#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1e9 + 7;
const long long P = 31;

int main() {
    string s = "abcabd";
    int n = s.size();
    vector<long long> h(n + 1, 0), pw(n + 1, 1);
    for (int i = 0; i < n; i++) {
        h[i + 1] = (h[i] * P + (s[i] - 'a' + 1)) % MOD;
        pw[i + 1] = (pw[i] * P) % MOD;
    }
    auto getHash = [&](int l, int r) {
        return ((h[r + 1] - h[l] * pw[r - l + 1]) % MOD + MOD) % MOD;
    };
    cout << "abc hash: " << getHash(0, 2) << " | abd hash: " << getHash(3, 5) << endl;
    cout << "ab hashes equal? " << boolalpha << (getHash(0, 1) == getHash(3, 4)) << endl;
}
