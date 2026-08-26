// sortsort · Kasai's Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kasai-algorithm

#include <bits/stdc++.h>
using namespace std;

vector<int> kasai(const string& s, const vector<int>& sa) {
    int n = s.size();
    vector<int> rank(n), lcp(n, 0);
    for (int i = 0; i < n; i++) rank[sa[i]] = i;
    int h = 0;
    for (int i = 0; i < n; i++) {
        if (rank[i] > 0) {
            int j = sa[rank[i] - 1];
            while (i + h < n && j + h < n && s[i + h] == s[j + h]) h++;
            lcp[rank[i]] = h;
            if (h > 0) h--;
        } else {
            h = 0;
        }
    }
    return lcp;
}

int main() {
    string s = "banana";
    vector<int> sa = {5, 3, 1, 0, 4, 2};
    for (int v : kasai(s, sa)) cout << v << " ";
    cout << endl;   // 0 1 3 0 0 2
}
