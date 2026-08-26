// Stepsort · Combination Sum
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/combination-sum

#include <bits/stdc++.h>
using namespace std;

vector<int> candidates = {2, 3, 6, 7};
int target = 7;

void combine(int start, int remaining, vector<int>& current, vector<vector<int>>& results) {
    if (remaining == 0) {
        results.push_back(current);
        return;
    }
    for (int i = start; i < (int)candidates.size(); i++) {
        if (candidates[i] > remaining) continue;
        current.push_back(candidates[i]);
        // reuse allowed: pass i, not i + 1
        combine(i, remaining - candidates[i], current, results);
        current.pop_back();
    }
}

int main() {
    vector<vector<int>> results;
    vector<int> current;
    combine(0, target, current, results);
    for (const auto& combo : results) {
        cout << "[";
        for (size_t i = 0; i < combo.size(); i++) {
            if (i) cout << " ";
            cout << combo[i];
        }
        cout << "]" << endl;
    }
    return 0;
}
