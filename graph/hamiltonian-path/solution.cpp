// sortsort · Hamiltonian Path
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamiltonian-path

#include <bits/stdc++.h>
using namespace std;

int n;
vector<vector<int>> adjMat;
vector<bool> visited;
vector<int> currentPath;

// Try to extend the path so every vertex appears exactly once
bool backtrack(int placed, int last) {
    if (placed == n) return true;
    for (int v = 0; v < n; v++) {
        if (visited[v]) continue;
        if (placed > 0 && adjMat[last][v] == 0) continue;  // must extend the path
        visited[v] = true;
        currentPath.push_back(v);
        if (backtrack(placed + 1, v)) return true;
        currentPath.pop_back();
        visited[v] = false;
    }
    return false;
}

int main() {
    vector<vector<int>> matrix = {
        {0, 1, 0, 1},
        {1, 0, 1, 1},
        {0, 1, 0, 1},
        {1, 1, 1, 0},
    };
    adjMat = matrix;
    n = (int)matrix.size();
    visited.assign(n, false);
    if (backtrack(0, -1)) {
        cout << "Hamiltonian path:";
        for (int v : currentPath) cout << " " << v;
        cout << endl;
    } else {
        cout << "No Hamiltonian path exists" << endl;
    }
    return 0;
}
