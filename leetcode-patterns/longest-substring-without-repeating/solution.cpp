// Stepsort · Longest Substring Without Repeating
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/longest-substring-without-repeating

#include <bits/stdc++.h>
using namespace std;

pair<int, string> longestUnique(const string& s) {
    vector<int> last(256, -1);  // last index of each character
    int start = 0, bestLen = 0, bestStart = 0;
    for (int i = 0; i < (int)s.size(); i++) {
        unsigned char c = s[i];
        if (last[c] >= start) start = last[c] + 1;
        last[c] = i;
        if (i - start + 1 > bestLen) {
            bestLen = i - start + 1;
            bestStart = start;
        }
    }
    return {bestLen, s.substr(bestStart, bestLen)};
}

int main() {
    pair<int, string> res = longestUnique("abcabcbb");
    cout << res.first << " " << res.second << endl;
    return 0;
}
