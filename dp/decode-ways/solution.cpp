// sortsort · Decode Ways
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/decode-ways

#include <bits/stdc++.h>
using namespace std;

// prev2 = ways for prefix ending two chars back, prev1 = one char back
int numDecodings(const string& s) {
    if (s.empty() || s[0] == '0') return 0;
    long long prev2 = 1, prev1 = 1;
    for (int i = 1; i < (int)s.size(); i++) {
        long long cur = 0;
        if (s[i] != '0') cur += prev1;  // single digit decode
        int two = (s[i - 1] - '0') * 10 + (s[i] - '0');
        if (two >= 10 && two <= 26) cur += prev2;  // two digit decode
        prev2 = prev1;
        prev1 = cur;
    }
    return (int)prev1;
}

int main() {
    vector<string> tests = {"12", "226", "06"};
    for (const string& t : tests) {
        cout << t << " decodes in " << numDecodings(t) << " ways" << endl;
    }
    return 0;
}
