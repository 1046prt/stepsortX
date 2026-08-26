// Stepsort · Permutations
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/permutations

#include <bits/stdc++.h>
using namespace std;

vector<int> items = {1, 2, 3};

void backtrack(vector<int>& current, vector<bool>& used) {
    if (current.size() == items.size()) {
        for (size_t i = 0; i < current.size(); i++) {
            if (i) cout << " ";
            cout << current[i];
        }
        cout << endl;
        return;
    }
    for (size_t i = 0; i < items.size(); i++) {
        if (!used[i]) {
            used[i] = true;
            current.push_back(items[i]);
            backtrack(current, used);
            current.pop_back();
            used[i] = false;
        }
    }
}

int main() {
    vector<int> current;
    vector<bool> used(items.size(), false);
    backtrack(current, used);
    return 0;
}
