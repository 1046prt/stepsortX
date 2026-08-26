// sortsort · Sudoku Solver
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sudoku-solver

#include <bits/stdc++.h>
using namespace std;

int grid[9][9] = {
    {5, 3, 0, 0, 7, 0, 0, 0, 0},
    {6, 0, 0, 1, 9, 5, 0, 0, 0},
    {0, 9, 8, 0, 0, 0, 0, 6, 0},
    {8, 0, 0, 0, 6, 0, 0, 0, 3},
    {4, 0, 0, 8, 0, 3, 0, 0, 1},
    {7, 0, 0, 0, 2, 0, 0, 0, 6},
    {0, 6, 0, 0, 0, 0, 2, 8, 0},
    {0, 0, 0, 4, 1, 9, 0, 0, 5},
    {0, 0, 0, 0, 8, 0, 0, 7, 9}
};

bool valid(int r, int c, int v) {
    for (int i = 0; i < 9; i++) {
        if (grid[r][i] == v || grid[i][c] == v) return false;
    }
    int br = 3 * (r / 3), bc = 3 * (c / 3);
    for (int i = br; i < br + 3; i++) {
        for (int j = bc; j < bc + 3; j++) {
            if (grid[i][j] == v) return false;
        }
    }
    return true;
}

bool solve() {
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            if (grid[r][c] == 0) {
                for (int v = 1; v <= 9; v++) {
                    if (valid(r, c, v)) {
                        grid[r][c] = v;
                        if (solve()) return true;
                        grid[r][c] = 0;
                    }
                }
                return false;
            }
        }
    }
    return true;
}

int main() {
    if (solve()) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) cout << grid[r][c] << " ";
            cout << endl;
        }
    } else {
        cout << "No solution exists" << endl;
    }
    return 0;
}
