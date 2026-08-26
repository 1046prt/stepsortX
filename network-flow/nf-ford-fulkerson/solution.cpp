// Stepsort · Ford-Fulkerson
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-ford-fulkerson

#include <bits/stdc++.h>
using namespace std;

// DFS that finds one augmenting path and pushes flow along it
int dfs(vector<vector<int>>& residual, vector<bool>& visited, int u, int t, int flow) {
    if (u == t) return flow;
    visited[u] = true;
    for (int v = 0; v < (int)residual.size(); ++v) {
        if (!visited[v] && residual[u][v] > 0) {
            int pushed = dfs(residual, visited, v, t, min(flow, residual[u][v]));
            if (pushed > 0) {
                residual[u][v] -= pushed;
                residual[v][u] += pushed;
                return pushed;
            }
        }
    }
    return 0;
}

int fordFulkerson(int n, vector<vector<int>> residual, int s, int t) {
    int maxFlow = 0;
    while (true) {
        vector<bool> visited(n, false);
        int pushed = dfs(residual, visited, s, t, INT_MAX);
        if (pushed == 0) break;
        maxFlow += pushed;
    }
    return maxFlow;
}

int main() {
    int n = 6;
    vector<vector<int>> capacity(n, vector<int>(n, 0));
    vector<vector<int>> edges = {
        {0, 1, 16}, {0, 2, 13},
        {1, 3, 12},
        {2, 1, 4}, {2, 4, 14},
        {3, 2, 9}, {3, 5, 20},
        {4, 3, 7}, {4, 5, 4}
    };
    for (const auto& e : edges) capacity[e[0]][e[1]] = e[2];
    cout << "Max flow: " << fordFulkerson(n, capacity, 0, 5) << endl;
    return 0;
}
