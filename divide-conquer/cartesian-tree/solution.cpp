// sortsort · Cartesian Tree
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cartesian-tree

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> values = {9, 3, 7, 1, 8, 12, 10, 20};
    int n = (int)values.size();
    vector<int> parent(n, -1), left(n, -1), right(n, -1);
    vector<int> stk;
    for (int i = 0; i < n; i++) {
        int last = -1;
        while (!stk.empty() && values[stk.back()] > values[i]) {
            last = stk.back();
            stk.pop_back();
        }
        if (last != -1) {
            left[i] = last;
            parent[last] = i;
        }
        if (!stk.empty()) {
            parent[i] = stk.back();
            right[stk.back()] = i;
        }
        stk.push_back(i);
    }
    cout << "parents:";
    for (int v = 0; v < n; v++) cout << " " << parent[v];
    cout << endl;
}
