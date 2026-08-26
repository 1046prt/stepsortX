// Stepsort · Tarjan's SCC
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tarjan-scc

#include <bits/stdc++.h>
using namespace std;

struct TarjanState {
    int counter = 0;
    vector<int> index, low;
    vector<bool> onStack;
    vector<int> stack;
};

void strongConnect(int u, const vector<vector<int>>& adj, TarjanState& st,
                   vector<vector<int>>& components) {
    st.index[u] = st.low[u] = st.counter++;
    st.stack.push_back(u);
    st.onStack[u] = true;
    for (int v : adj[u]) {
        if (st.index[v] == -1) {
            strongConnect(v, adj, st, components);
            st.low[u] = min(st.low[u], st.low[v]);
        } else if (st.onStack[v]) {
            st.low[u] = min(st.low[u], st.index[v]);
        }
    }
    if (st.low[u] == st.index[u]) {  // u is the root of an SCC
        vector<int> component;
        while (true) {
            int w = st.stack.back();
            st.stack.pop_back();
            st.onStack[w] = false;
            component.push_back(w);
            if (w == u) break;
        }
        sort(component.begin(), component.end());
        components.push_back(component);
    }
}

vector<vector<int>> tarjanScc(int V, const vector<vector<int>>& adj) {
    TarjanState st;
    st.index.assign(V, -1);
    st.low.assign(V, 0);
    st.onStack.assign(V, false);
    vector<vector<int>> components;
    for (int u = 0; u < V; u++)
        if (st.index[u] == -1)
            strongConnect(u, adj, st, components);
    return components;
}

int main() {
    vector<vector<int>> adj = {
        {1}, {2}, {0, 3}, {4}, {3, 5}, {}, {7, 4}, {5, 6},
    };
    for (const vector<int>& component : tarjanScc(8, adj)) {
        cout << "SCC:";
        for (int v : component) cout << " " << v;
        cout << endl;
    }
    return 0;
}
