// Stepsort · Word Break
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/word-break

#include <bits/stdc++.h>
using namespace std;

// Fills out with one valid segmentation; returns false if impossible
bool wordBreak(const string& s, const vector<string>& dict, vector<string>& out) {
    unordered_set<string> words(dict.begin(), dict.end());
    int n = s.size();
    vector<bool> dp(n + 1, false);  // dp[i]: prefix of length i is breakable
    vector<int> parent(n + 1, -1);  // start index of the word ending at i
    dp[0] = true;
    for (int i = 1; i <= n; ++i) {
        for (int j = 0; j < i; ++j) {
            if (dp[j] && words.count(s.substr(j, i - j))) {
                dp[i] = true;
                parent[i] = j;
                break;
            }
        }
    }
    if (!dp[n]) return false;
    int i = n;
    while (i > 0) {
        out.push_back(s.substr(parent[i], i - parent[i]));
        i = parent[i];
    }
    reverse(out.begin(), out.end());
    return true;
}

int main() {
    vector<string> dictionary = {"cat", "cats", "and", "sand", "dog"};
    vector<string> parts;
    cout << boolalpha;
    cout << "catsanddog breakable: " << wordBreak("catsanddog", dictionary, parts) << endl;
    cout << "Segmentation:";
    for (const string& w : parts) cout << " " << w;
    cout << endl;
    parts.clear();
    cout << "catsandog breakable: " << wordBreak("catsandog", dictionary, parts) << endl;
    return 0;
}
