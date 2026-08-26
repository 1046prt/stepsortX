// Stepsort · Hamiltonian Cycle (BT)
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamiltonian-bt

#include <bits/stdc++.h>
using namespace std;

const int V = 5;
int graph[V][V] = {
    {0, 1, 0, 1, 0},
    {1, 0, 1, 1, 1},
    {0, 1, 0, 0, 1},
    {1, 1, 0, 0, 1},
    {0, 1, 1, 1, 0}
};

bool isSafe(int v, const vector<int>& path, int pos) {
    if (graph[path[pos - 1]][v] == 0) return false;
    for (int u : path) {
        if (u == v) return false;
    }
    return true;
}

bool hamCycle(vector<int>& path, int pos) {
    if (pos == V) return graph[path[pos - 1]][path[0]] == 1;
    for (int v = 1; v < V; v++) {
        if (isSafe(v, path, pos)) {
            path[pos] = v;
            if (hamCycle(path, pos + 1)) return true;
            path[pos] = -1;
        }
    }
    return false;
}

int main() {
    vector<int> path(V, -1);
    path[0] = 0;
    if (hamCycle(path, 1)) {
        cout << "Hamiltonian cycle:";
        for (int v : path) cout << " " << v;
        cout << " " << path[0] << endl;
    } else {
        cout << "No Hamiltonian cycle exists" << endl;
    }
    return 0;
}
