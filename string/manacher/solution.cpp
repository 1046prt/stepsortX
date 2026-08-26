// Stepsort · Manacher's Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/manacher

#include <bits/stdc++.h>
using namespace std;

// Longest palindromic substring in O(n) using Manacher's algorithm.
string manacher(const string& s) {
    string t = "#";
    for (char c : s) { t += c; t += '#'; }
    int n = t.size();
    vector<int> p(n, 0);
    int center = 0, right = 0;
    int bestLen = 0, bestCenter = 0;

    for (int i = 0; i < n; i++) {
        if (i < right) p[i] = min(right - i, p[2 * center - i]);
        while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n
               && t[i - p[i] - 1] == t[i + p[i] + 1]) p[i]++;
        if (i + p[i] > right) { center = i; right = i + p[i]; }
        if (p[i] > bestLen) { bestLen = p[i]; bestCenter = i; }
    }
    return s.substr((bestCenter - bestLen) / 2, bestLen);
}

int main() {
    vector<string> tests = {"babad", "cbbd", "forgeeksskeegfor"};
    for (const string& s : tests) {
        cout << s << " -> " << manacher(s) << endl;
    }
    return 0;
}
