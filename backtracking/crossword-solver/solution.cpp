// sortsort · Crossword Solver
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/crossword-solver

#include <bits/stdc++.h>
using namespace std;

vector<string> words = {"hi", "world", "code"};

struct Slot {
    int r, start, length;
};

void findSlots(const vector<string>& pattern, vector<Slot>& slots) {
    for (int r = 0; r < (int)pattern.size(); r++) {
        int c = 0;
        while (c < (int)pattern[r].size()) {
            if (pattern[r][c] == '-') {
                int start = c;
                while (c < (int)pattern[r].size() && pattern[r][c] == '-') c++;
                slots.push_back({r, start, c - start});
            } else {
                c++;
            }
        }
    }
}

bool solve(vector<vector<char>>& grid, const vector<Slot>& slots, size_t idx, vector<bool>& used) {
    if (idx == slots.size()) return true;
    Slot s = slots[idx];
    for (size_t wi = 0; wi < words.size(); wi++) {
        if (!used[wi] && (int)words[wi].size() == s.length) {
            used[wi] = true;
            for (int j = 0; j < s.length; j++) grid[s.r][s.start + j] = words[wi][j];
            if (solve(grid, slots, idx + 1, used)) return true;
            for (int j = 0; j < s.length; j++) grid[s.r][s.start + j] = '-';  // undo
            used[wi] = false;
        }
    }
    return false;
}

int main() {
    vector<string> pattern = {"--+----", "-----+"};
    vector<vector<char>> grid;
    for (const string& row : pattern) {
        grid.push_back(vector<char>(row.begin(), row.end()));
    }
    vector<Slot> slots;
    findSlots(pattern, slots);
    vector<bool> used(words.size(), false);
    if (solve(grid, slots, 0, used)) {
        for (const auto& row : grid) {
            for (char ch : row) cout << ch;
            cout << endl;
        }
    } else {
        cout << "No solution" << endl;
    }
    return 0;
}
