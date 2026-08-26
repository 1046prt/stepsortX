// sortsort · Merge Intervals
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-intervals

#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> mergeIntervals(vector<vector<int>> intervals) {
    sort(intervals.begin(), intervals.end());
    vector<vector<int>> merged;
    for (auto& interval : intervals) {
        if (!merged.empty() && merged.back()[1] >= interval[0]) {
            merged.back()[1] = max(merged.back()[1], interval[1]);
        } else {
            merged.push_back(interval);
        }
    }
    return merged;
}

void printIntervals(const vector<vector<int>>& ivs) {
    cout << "[";
    for (size_t i = 0; i < ivs.size(); i++) {
        if (i > 0) cout << ", ";
        cout << "[" << ivs[i][0] << ", " << ivs[i][1] << "]";
    }
    cout << "]" << endl;
}

int main() {
    vector<vector<int>> intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    printIntervals(mergeIntervals(intervals));
    return 0;
}
