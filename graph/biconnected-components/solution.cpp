// sortsort · Biconnected Components
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/biconnected-components

#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj;
vector<int> disc_, low_;
vector<pair<int,int>> st;
vector<vector<int>> comps;
int timer_ = 0;

void dfs(int u, int pe) {
    disc_[u] = low_[u] = ++timer_;
    for (auto& [v, ei] : adj[u]) {
        if (ei == pe) continue;
        if (disc_[v] == -1) {
            st.push_back({u, v});
            dfs(v, ei);
            low_[u] = min(low_[u], low_[v]);
            if (low_[v] >= disc_[u]) {
                set<int> nodes;
                while (st.back() != make_pair(u, v)) {
                    auto [a, b] = st.back(); st.pop_back();
                    nodes.insert(a); nodes.insert(b);
                }
                auto [a, b] = st.back(); st.pop_back();
                nodes.insert(a); nodes.insert(b);
                comps.push_back(vector<int>(nodes.begin(), nodes.end()));
            }
        } else if (disc_[v] < disc_[u]) {
            st.push_back({u, v});
            low_[u] = min(low_[u], disc_[v]);
        }
    }
}

int main() {
    int n = 6;
    vector<pair<int,int>> edges = {{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}};
    adj.resize(n);
    int ei = 0;
    for (auto& [u, v] : edges) { adj[u].push_back({v, ei}); adj[v].push_back({u, ei}); ei++; }
    disc_.assign(n, -1); low_.assign(n, -1);
    for (int s = 0; s < n; s++) if (disc_[s] == -1) dfs(s, -1);
    cout << comps.size() << " biconnected components:" << endl;
    for (auto& c : comps) {
        cout << " ";
        for (int v : c) cout << " " << v;
        cout << endl;
    }
}
