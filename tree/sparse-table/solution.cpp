// sortsort · Sparse Table (RMQ)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sparse-table

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> arr = {4, 2, 8, 1, 6, 3, 7, 5};
    int n = arr.size();
    int LOG = (int)log2(n) + 1;
    vector<vector<int>> sp(LOG, vector<int>(n));
    sp[0] = arr;
    for (int j = 1; j < LOG; j++) {
        int len = 1 << j;
        for (int i = 0; i + len <= n; i++)
            sp[j][i] = min(sp[j - 1][i], sp[j - 1][i + (len >> 1)]);
    }
    auto query = [&](int l, int r) {
        int k = (int)log2(r - l + 1);
        return min(sp[k][l], sp[k][r - (1 << k) + 1]);
    };
    cout << query(2, 6) << endl;   // 1
    cout << query(0, 3) << endl;   // 2
}
