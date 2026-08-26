// Stepsort · N-Queens
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/n-queens

#include <bits/stdc++.h>
using namespace std;

const int N = 4;
vector<int> board_pos;

bool safe(int row, int col) {
    for (int r = 0; r < row; r++) {
        if (board_pos[r] == col || abs(board_pos[r] - col) == row - r) return false;
    }
    return true;
}

bool place(int row) {
    if (row == N) return true;
    for (int col = 0; col < N; col++) {
        if (safe(row, col)) {
            board_pos[row] = col;
            if (place(row + 1)) return true;
            board_pos[row] = -1;
        }
    }
    return false;
}

int main() {
    board_pos.assign(N, -1);
    if (place(0)) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                cout << (board_pos[r] == c ? "Q " : ". ");
            }
            cout << endl;
        }
    } else {
        cout << "No solution" << endl;
    }
    return 0;
}
