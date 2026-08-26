// sortsort · DSU on Tree (Small-to-Large)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dsu-on-tree

#include <bits/stdc++.h>
using namespace std;

int main() {
    int n = 12;
    vector<int> parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
    vector<int> color = {1, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1};
    vector<vector<int>> ch(n);
    int root = 0;
    for (int v = 0; v < n; v++) {
        if (parent[v] == -1) root = v;
        else ch[parent[v]].push_back(v);
    }
    vector<int> sz(n, 1), order;
    vector<pair<int, bool>> stk;
    stk.push_back({root, false});
    while (!stk.empty()) {
        pair<int, bool> top = stk.back();
        stk.pop_back();
        int v = top.first;
        if (top.second) {
            order.push_back(v);
            for (int c : ch[v]) sz[v] += sz[c];
        } else {
            stk.push_back({v, true});
            for (int c : ch[v]) stk.push_back({c, false});
        }
    }
    vector<unordered_set<int>> maps(n);
    vector<int> ans(n);
    for (int v : order) {
        int big = -1;
        for (int c : ch[v]) if (big == -1 || sz[c] > sz[big]) big = c;
        if (big == -1) {
            maps[v].insert(color[v]);
        } else {
            maps[v].swap(maps[big]);
            maps[v].insert(color[v]);
            for (int c : ch[v]) {
                if (c != big) {
                    maps[v].insert(maps[c].begin(), maps[c].end());
                    maps[c].clear();
                }
            }
        }
        ans[v] = (int)maps[v].size();
    }
    for (int v = 0; v < n; v++) {
        cout << ans[v];
        if (v + 1 < n) cout << " ";
    }
    cout << endl;
}
