// sortsort · Rat in a Maze
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rat-in-maze

#include <bits/stdc++.h>
using namespace std;

const int N = 4;
int maze[N][N] = {
    {1, 0, 0, 0},
    {1, 1, 0, 1},
    {0, 1, 0, 0},
    {0, 1, 1, 1}
};
int path[N][N];

bool go(int r, int c) {
    if (r == N - 1 && c == N - 1 && maze[r][c] == 1) {
        path[r][c] = 1;
        return true;
    }
    if (r >= 0 && r < N && c >= 0 && c < N && maze[r][c] == 1 && path[r][c] == 0) {
        path[r][c] = 1;
        if (go(r + 1, c) || go(r, c + 1) || go(r - 1, c) || go(r, c - 1)) return true;
        path[r][c] = 0;
    }
    return false;
}

int main() {
    memset(path, 0, sizeof(path));
    if (go(0, 0)) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) cout << path[r][c] << " ";
            cout << endl;
        }
    } else {
        cout << "No path found" << endl;
    }
    return 0;
}
