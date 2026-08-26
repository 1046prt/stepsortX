// sortsort · Heavy-Light Decomposition
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heavy-light-decomposition

#include <bits/stdc++.h>
using namespace std;

int main() {
    int n = 12;
    vector<int> parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
    vector<vector<int>> kids(n);
    for (int v = 0; v < n; v++)
        if (parent[v] >= 0) kids[parent[v]].push_back(v);

    vector<int> depth(n);
    for (int v = 0; v < n; v++) {
        int cur = v, d = 0;
        while (parent[cur] != -1) { cur = parent[cur]; d++; }
        depth[v] = d;
    }

    vector<int> order(n);
    iota(order.begin(), order.end(), 0);
    sort(order.begin(), order.end(), [&](int a, int b){ return depth[a] > depth[b]; });

    vector<int> size(n, 1);
    for (int v : order) for (int c : kids[v]) size[v] += size[c];

    vector<int> chainId(n, -1), head(n, -1);
    int chains = 0;
    stack<int> roots;
    roots.push(0);
    while (!roots.empty()) {
        int start = roots.top(); roots.pop();
        head[start] = start;
        chainId[start] = chains;
        int cur = start;
        while (!kids[cur].empty()) {
            int heavy = *max_element(kids[cur].begin(), kids[cur].end(),
                          [&](int a, int b){ return size[a] < size[b]; });
            head[heavy] = head[cur];
            chainId[heavy] = chains;
            cur = heavy;
            for (int c : kids[cur])
                if (c != heavy) roots.push(c);
        }
        chains++;
    }
    cout << chains << " chains" << endl;
}
