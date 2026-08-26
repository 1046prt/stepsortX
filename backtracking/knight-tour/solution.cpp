// Stepsort · Knight's Tour
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knight-tour

#include <bits/stdc++.h>
using namespace std;

const int N = 5;
int dr[8] = {2, 1, -1, -2, -2, -1, 1, 2};
int dc[8] = {1, 2, 2, 1, -1, -2, -2, -1};
int board[N][N];

int degree(int r, int c) {
    // Warnsdorff heuristic: count onward moves for ordering
    int d = 0;
    for (int k = 0; k < 8; k++) {
        int nr = r + dr[k], nc = c + dc[k];
        if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) d++;
    }
    return d;
}

bool tour(int r, int c, int step) {
    if (step == N * N) return true;
    vector<array<int, 3>> candidates;
    for (int k = 0; k < 8; k++) {
        int nr = r + dr[k], nc = c + dc[k];
        if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) {
            candidates.push_back({degree(nr, nc), nr, nc});
        }
    }
    sort(candidates.begin(), candidates.end());
    for (const auto& t : candidates) {
        board[t[1]][t[2]] = step + 1;
        if (tour(t[1], t[2], step + 1)) return true;
        board[t[1]][t[2]] = 0;
    }
    return false;
}

int main() {
    memset(board, 0, sizeof(board));
    board[0][0] = 1;
    if (tour(0, 0, 1)) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) cout << setw(3) << board[r][c];
            cout << endl;
        }
    } else {
        cout << "No tour found" << endl;
    }
    return 0;
}
