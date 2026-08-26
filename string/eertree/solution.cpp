// sortsort · Eertree (Palindromic Tree)
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/eertree

#include <bits/stdc++.h>
using namespace std;

struct PalNode { int len, suff; };

int main() {
    string s = "abba";
    vector<PalNode> tree = {{-1, 0}, {0, 0}};
    int last = 1;
    vector<string> created;

    for (int i = 0; i < (int)s.size(); i++) {
        char c = s[i];
        int x = last;
        while (true) {
            int start = i - tree[x].len - 1;
            if (start >= 0 && s[start] == c) break;
            x = tree[x].suff;
        }
        int candLen = tree[x].len + 2;
        int found = -1;
        for (int t = 2; t < (int)tree.size(); t++)
            if (tree[t].len == candLen) found = t;

        if (found != -1) {
            last = found;
            continue;
        }
        int suffLen = max(tree[x].len, 0);
        int suffTarget = 1;
        for (int t = 2; t < (int)tree.size(); t++)
            if (tree[t].len == suffLen) { suffTarget = t; break; }

        tree.push_back({candLen, suffTarget});
        last = tree.size() - 1;
        created.push_back("len " + to_string(candLen));
    }
    cout << "new palindrome nodes:" << endl;
    for (auto& c : created) cout << "  " << c << endl;
    cout << "distinct: " << tree.size() - 2 << endl;   // 4
}
