// Stepsort · Flood Fill
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/flood-fill

#include <bits/stdc++.h>
using namespace std;

void floodFill(vector<vector<int>>& grid, int sr, int sc, int newColor) {
    int rows = grid.size(), cols = grid[0].size();
    int original = grid[sr][sc];
    if (original == newColor) return;
    function<void(int,int)> dfs = [&](int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return;
        if (grid[r][c] != original) return;
        grid[r][c] = newColor;
        dfs(r+1, c); dfs(r-1, c); dfs(r, c+1); dfs(r, c-1);
    };
    dfs(sr, sc);
}

int main() {
    vector<vector<int>> grid = {{1,1,1},{1,1,0},{1,0,1}};
    floodFill(grid, 1, 1, 2);
    for (auto& row : grid) {
        for (int x : row) cout << x << " ";
        cout << endl;
    }
    return 0;
}
