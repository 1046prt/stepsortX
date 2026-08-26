// sortsort · Word Search
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/word-search

#include <bits/stdc++.h>
using namespace std;

vector<vector<char>> grid = {
    {'A', 'B', 'C', 'E'},
    {'S', 'F', 'C', 'S'},
    {'A', 'D', 'E', 'E'}
};

bool dfs(vector<vector<char>>& g, const string& word, int r, int c, int idx) {
    if (idx == (int)word.size()) return true;
    int rows = g.size(), cols = g[0].size();
    if (r < 0 || r >= rows || c < 0 || c >= cols || g[r][c] != word[idx]) return false;
    char saved = g[r][c];
    g[r][c] = '#';  // mark visited
    bool found = dfs(g, word, r + 1, c, idx + 1) || dfs(g, word, r - 1, c, idx + 1) ||
                 dfs(g, word, r, c + 1, idx + 1) || dfs(g, word, r, c - 1, idx + 1);
    g[r][c] = saved;  // unmark
    return found;
}

bool exist(vector<vector<char>> g, const string& word) {
    for (int r = 0; r < (int)g.size(); r++) {
        for (int c = 0; c < (int)g[0].size(); c++) {
            if (dfs(g, word, r, c, 0)) return true;
        }
    }
    return false;
}

int main() {
    vector<string> words = {"ABCCED", "SEE", "ABCB"};
    for (const string& w : words) {
        cout << w << " -> " << (exist(grid, w) ? "true" : "false") << endl;
    }
    return 0;
}
