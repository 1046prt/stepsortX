// sortsort · KMP Pattern Matching
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kmp

#include <bits/stdc++.h>
using namespace std;

// lps[i] = length of the longest proper prefix of pattern[0..i]
// that is also a suffix of it.
vector<int> buildLps(const string& pattern) {
    int m = pattern.size();
    vector<int> lps(m, 0);
    int length = 0;
    int i = 1;
    while (i < m) {
        if (pattern[i] == pattern[length]) {
            length++;
            lps[i] = length;
            i++;
        } else if (length > 0) {
            length = lps[length - 1];
        } else {
            i++;
        }
    }
    return lps;
}

// Scan the text once, falling back along the LPS table on mismatch.
vector<int> kmpSearch(const string& text, const string& pattern) {
    vector<int> matches;
    int n = text.size(), m = pattern.size();
    if (m == 0 || m > n) return matches;
    vector<int> lps = buildLps(pattern);
    int i = 0, j = 0;
    while (i < n) {
        if (text[i] == pattern[j]) {
            i++;
            j++;
            if (j == m) {
                matches.push_back(i - m);
                j = lps[j - 1];
            }
        } else if (j > 0) {
            j = lps[j - 1];
        } else {
            i++;
        }
    }
    return matches;
}

int main() {
    string text = "AABAACAADAABAABA";
    string pattern = "AABA";
    cout << "text: " << text << endl;
    cout << "pattern: " << pattern << endl;
    cout << "lps table:";
    for (int value : buildLps(pattern)) cout << " " << value;
    cout << endl;
    cout << "found at:";
    for (int pos : kmpSearch(text, pattern)) cout << " " << pos;
    cout << endl;
    return 0;
}
