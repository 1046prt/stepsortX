// sortsort · Interval Scheduling
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interval-scheduling

#include <bits/stdc++.h>
using namespace std;

vector<pair<int, int>> intervalScheduling(vector<pair<int, int>> intervals) {
    // earliest finish time first maximizes non-overlapping intervals
    sort(intervals.begin(), intervals.end(),
         [](const pair<int, int>& a, const pair<int, int>& b) { return a.second < b.second; });
    vector<pair<int, int>> chosen;
    int lastEnd = INT_MIN;
    for (const pair<int, int>& iv : intervals) {
        if (chosen.empty() || iv.first >= lastEnd) {
            chosen.push_back(iv);
            lastEnd = iv.second;
        }
    }
    return chosen;
}

int main() {
    vector<pair<int, int>> intervals = {{1, 3}, {2, 4}, {3, 5}, {0, 7}, {5, 8}, {6, 9}};
    vector<pair<int, int>> chosen = intervalScheduling(intervals);
    cout << "Maximum intervals: " << chosen.size() << endl;
    for (const pair<int, int>& iv : chosen) {
        cout << "[" << iv.first << ", " << iv.second << "]" << endl;
    }
    return 0;
}
