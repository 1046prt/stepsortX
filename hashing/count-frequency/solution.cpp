// Stepsort · Frequency Count
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-frequency

#include <bits/stdc++.h>
using namespace std;

map<int, int> count_frequency(const vector<int>& data) {
    map<int, int> counts;
    for (int x : data) counts[x]++;
    return counts;
}

int main() {
    vector<int> data = {4, 2, 7, 4, 8, 2, 4, 9, 7, 4};
    map<int, int> counts = count_frequency(data);
    int best_key = data[0];
    for (const auto& entry : counts) {
        cout << entry.first << " occurs "
             << entry.second << " time(s)" << endl;
        if (entry.second > counts[best_key]) best_key = entry.first;
    }
    cout << "most frequent: " << best_key
         << " (" << counts[best_key] << " times)" << endl;
    return 0;
}
