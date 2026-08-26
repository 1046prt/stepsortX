// Stepsort · Suffix Array
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-array

#include <bits/stdc++.h>
using namespace std;

// Lexicographic order between two suffixes, compared character by character.
bool suffixLess(const string& text, int i, int j) {
    int n = text.size();
    while (i < n && j < n && text[i] == text[j]) { i++; j++; }
    if (i >= n) return true;
    if (j >= n) return false;
    return text[i] < text[j];
}

int main() {
    string text = "banana";
    int n = text.size();
    vector<int> sa(n);
    iota(sa.begin(), sa.end(), 0);

    sort(sa.begin(), sa.end(),
         [&](int i, int j) { return suffixLess(text, i, j); });

    cout << "text: " << text << endl;
    cout << "suffix array:";
    for (int v : sa) cout << " " << v;
    cout << endl;
    for (int v : sa) cout << v << ": " << text.substr(v) << endl;
    return 0;
}
