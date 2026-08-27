// Stepsort · Multi-Source BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-source-bfs

#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> multiSourceBFS(vector<vector<int>>& grid, vector<pair<int,int>>& sources) {
    int rows = grid.size(), cols = grid[0].size();
    vector<vector<int>> dist(rows, vector<int>(cols, -1));
    queue<pair<int,int>> q;
    for (auto& [r, c] : sources) { dist[r][c] = 0; q.push({r, c}); }
    int dirs[4][2] = {{0,1},{0,-1},{1,0},{-1,0}};
    while (!q.empty()) {
        auto [r, c] = q.front(); q.pop();
        for (auto& [dr, dc] : dirs) {
            int nr = r + dr, nc = c + dc;
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && dist[nr][nc] == -1) {
                dist[nr][nc] = dist[r][c] + 1;
                q.push({nr, nc});
            }
        }
    }
    return dist;
}

int main() {
    vector<vector<int>> grid(3, vector<int>(4, 0));
    vector<pair<int,int>> sources = {{0,0}, {2,3}};
    auto dist = multiSourceBFS(grid, sources);
    for (auto& row : dist) {
        for (int d : row) cout << d << " ";
        cout << endl;
    }
    return 0;
}
