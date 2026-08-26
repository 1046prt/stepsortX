// Stepsort · Kosaraju's SCC
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kosaraju-scc

#include <bits/stdc++.h>
using namespace std;

// First pass on G: record vertices by DFS finish time
void finishOrder(const vector<vector<int>>& g, int v, vector<bool>& visited, vector<int>& order) {
    visited[v] = true;
    for (int u : g[v]) {
        if (!visited[u]) finishOrder(g, u, visited, order);
    }
    order.push_back(v);
}

// Second pass on reversed G: gather one component
void collectComponent(const vector<vector<int>>& g, int v, vector<bool>& visited, vector<int>& comp) {
    visited[v] = true;
    comp.push_back(v);
    for (int u : g[v]) {
        if (!visited[u]) collectComponent(g, u, visited, comp);
    }
}

vector<vector<int>> kosaraju(int n, const vector<pair<int, int>>& edges) {
    vector<vector<int>> g(n), rg(n);
    for (const pair<int, int>& e : edges) {
        g[e.first].push_back(e.second);
        rg[e.second].push_back(e.first);
    }
    vector<bool> visited(n, false);
    vector<int> order;
    for (int v = 0; v < n; v++) {
        if (!visited[v]) finishOrder(g, v, visited, order);
    }
    fill(visited.begin(), visited.end(), false);
    vector<vector<int>> components;
    for (int i = n - 1; i >= 0; i--) {  // decreasing finish time
        if (!visited[order[i]]) {
            vector<int> comp;
            collectComponent(rg, order[i], visited, comp);
            sort(comp.begin(), comp.end());
            components.push_back(comp);
        }
    }
    return components;
}

int main() {
    vector<pair<int, int>> edges = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}};
    cout << "Strongly connected components:" << endl;
    for (const vector<int>& comp : kosaraju(5, edges)) {
        for (int v : comp) cout << v << " ";
        cout << endl;
    }
    return 0;
}
