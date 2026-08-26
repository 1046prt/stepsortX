// sortsort · Anagram Check
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/anagram-check

#include <bits/stdc++.h>
using namespace std;

// Frequency count over the 26 lowercase letters.
bool isAnagram(const string& a, const string& b) {
    if (a.size() != b.size()) return false;
    vector<int> counts(26, 0);
    for (char ch : a) counts[ch - 'a']++;
    for (char ch : b) {
        counts[ch - 'a']--;
        if (counts[ch - 'a'] < 0) return false; // b needs a letter a lacks
    }
    return true; // equal lengths plus no deficit implies no surplus
}

int main() {
    vector<pair<string, string>> pairs = {
        {"listen", "silent"},
        {"triangle", "integral"},
        {"hello", "world"},
        {"aab", "abb"},
    };
    for (auto& [x, y] : pairs) {
        cout << x << " vs " << y << " -> "
             << (isAnagram(x, y) ? "true" : "false") << endl;
    }
    return 0;
}
