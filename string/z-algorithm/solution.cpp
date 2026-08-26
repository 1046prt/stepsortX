// Stepsort · Z Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/z-algorithm

#include <bits/stdc++.h>
using namespace std;

// z[i] = length of the longest common prefix of s and s[i..].
vector<int> zFunction(const string& s) {
    int n = s.size();
    vector<int> z(n, 0);
    z[0] = n;
    int left = 0, right = 0; // rightmost match window found so far
    for (int i = 1; i < n; i++) {
        if (i < right) { // reuse information from the previous window
            z[i] = min(right - i, z[i - left]);
        }
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) z[i]++;
        if (i + z[i] > right) { // extend the match window
            left = i;
            right = i + z[i];
        }
    }
    return z;
}

int main() {
    string text = "aabxaabxcaabxaabxay";
    string pattern = "aabx";

    // A '#' separator never appears in the data, so any maximal
    // prefix match crossing it must end exactly at the separator.
    string combined = pattern + "#" + text;
    vector<int> z = zFunction(combined);

    cout << "combined: " << combined << endl;
    cout << "z-array:";
    for (int value : z) cout << " " << value;
    cout << endl;

    cout << "matches:";
    int m = pattern.size();
    for (int i = m + 1; i < (int)combined.size(); i++) {
        if (z[i] == m) cout << " " << i - m - 1;
    }
    cout << endl;
    return 0;
}
