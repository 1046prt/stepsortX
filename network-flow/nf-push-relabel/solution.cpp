// Stepsort · Push-Relabel
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-push-relabel

#include <bits/stdc++.h>
using namespace std;

int pushRelabel(int n, vector<vector<int>> capacity, int s, int t) {
    vector<vector<int>> residual = capacity;
    vector<int> height(n, 0), excess(n, 0);
    vector<bool> active(n, false);
    queue<int> q;

    auto enqueue = [&](int v) {
        if (v != s && v != t && !active[v] && excess[v] > 0) {
            active[v] = true;
            q.push(v);
        }
    };

    height[s] = n;
    for (int v = 0; v < n; ++v) {
        if (residual[s][v] > 0) {
            excess[v] += residual[s][v];
            residual[v][s] += residual[s][v];
            residual[s][v] = 0;
            enqueue(v);
        }
    }

    while (!q.empty()) {
        int u = q.front();
        q.pop();
        active[u] = false;
        // Discharge u: push on admissible edges, relabel when stuck
        while (excess[u] > 0) {
            bool moved = false;
            for (int v = 0; v < n && excess[u] > 0; ++v) {
                if (residual[u][v] > 0 && height[u] == height[v] + 1) {
                    int amount = min(excess[u], residual[u][v]);
                    residual[u][v] -= amount;
                    residual[v][u] += amount;
                    excess[u] -= amount;
                    excess[v] += amount;
                    enqueue(v);
                    moved = true;
                }
            }
            if (!moved) {
                int lowest = INT_MAX;
                for (int v = 0; v < n; ++v) {
                    if (residual[u][v] > 0) lowest = min(lowest, height[v]);
                }
                height[u] = lowest + 1;
            }
        }
    }
    return excess[t];
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
    cout << "Max flow: " << pushRelabel(n, capacity, 0, 5) << endl;
    return 0;
}
