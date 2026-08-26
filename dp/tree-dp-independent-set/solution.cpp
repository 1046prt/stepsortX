// Stepsort · Tree DP (Max Independent Set)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-dp-independent-set

#include <bits/stdc++.h>
using namespace std;

vector<int> parent, weight_;
vector<vector<int>> kids;
pair<long long,long long> dfs(int v) {
    long long take = weight_[v], skip = 0;
    for (int c : kids[v]) {
        auto [tc, sc] = dfs(c);
        take += sc;
        skip += max(tc, sc);
    }
    return {take, skip};
}

int main() {
    parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
    weight_ = {3, 2, 1, 4, 2, 1, 3, 2, 5, 1, 2, 4};
    int n = parent.size();
    kids.resize(n);
    for (int v = 0; v < n; v++)
        if (parent[v] >= 0) kids[parent[v]].push_back(v);
    auto [t, s] = dfs(0);
    cout << "max independent set weight: " << max(t, s) << endl;
}
