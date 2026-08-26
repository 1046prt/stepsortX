// Stepsort · Rabin-Karp
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rabin-karp

#include <bits/stdc++.h>
using namespace std;

const long long BASE = 256;        // alphabet size
const long long MOD = 100000007LL; // prime modulus

// Slide a rolling hash over every window of length m. Hash hits are
// verified by direct comparison, so a collision between different
// strings can never be reported as a match.
vector<int> rabinKarpSearch(const string& text, const string& pattern) {
    vector<int> matches;
    int n = text.size(), m = pattern.size();
    if (m == 0 || m > n) return matches;

    long long highOrder = 1;        // weight of the leading character
    for (int i = 1; i < m; i++) highOrder = highOrder * BASE % MOD;

    long long pHash = 0, tHash = 0; // pattern hash and window hash
    for (int i = 0; i < m; i++) {
        pHash = (pHash * BASE + pattern[i]) % MOD;
        tHash = (tHash * BASE + text[i]) % MOD;
    }

    for (int start = 0; start + m <= n; start++) {
        if (pHash == tHash && text.compare(start, m, pattern) == 0) {
            matches.push_back(start);
        }
        if (start + m < n) { // roll the window one character to the right
            tHash = ((tHash - text[start] * highOrder) * BASE
                     + text[start + m]) % MOD;
            if (tHash < 0) tHash += MOD;
        }
    }
    return matches;
}

int main() {
    string text = "ababcababd";
    string pattern = "abab";
    vector<int> hits = rabinKarpSearch(text, pattern);
    cout << "matches at:";
    for (int idx : hits) cout << " " << idx;
    cout << endl;
    return 0;
}
