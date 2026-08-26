// sortsort · Boyer-Moore
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boyer-moore

#include <bits/stdc++.h>
using namespace std;

const int ALPHABET_SIZE = 256;

vector<int> badCharacterTable(const string& pattern) {
    vector<int> table(ALPHABET_SIZE, -1);
    for (int i = 0; i < (int)pattern.size(); i++) {
        table[(unsigned char)pattern[i]] = i;
    }
    return table;
}

vector<int> boyerMooreSearch(const string& text, const string& pattern) {
    vector<int> matches;
    int n = text.size(), m = pattern.size();
    if (m == 0 || m > n) return matches;
    vector<int> bad = badCharacterTable(pattern);
    int shift = 0;

    while (shift <= n - m) {
        int j = m - 1;
        while (j >= 0 && pattern[j] == text[shift + j]) j--;
        if (j < 0) {
            matches.push_back(shift);
            if (shift + m < n) shift += m - bad[(unsigned char)text[shift + m]];
            else shift += 1;
        } else {
            shift += max(1, j - bad[(unsigned char)text[shift + j]]);
        }
    }
    return matches;
}

void printMatches(const vector<int>& matches) {
    cout << "matches at:";
    for (int pos : matches) cout << " " << pos;
    cout << endl;
}

int main() {
    printMatches(boyerMooreSearch("ABAAABCDABABCD", "ABC"));
    printMatches(boyerMooreSearch("AABAACAADAABAABA", "AABA"));
    return 0;
}
