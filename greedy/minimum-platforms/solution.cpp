// sortsort · Minimum Platforms
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/minimum-platforms

#include <bits/stdc++.h>
using namespace std;

int minimumPlatforms(vector<int> arrivals, vector<int> departures) {
    // sweep two sorted timelines with two pointers
    sort(arrivals.begin(), arrivals.end());
    sort(departures.begin(), departures.end());
    size_t i = 0, j = 0;
    int platforms = 0;
    int maxNeeded = 0;
    while (i < arrivals.size()) {
        if (arrivals[i] <= departures[j]) {
            platforms++;
            maxNeeded = max(maxNeeded, platforms);
            i++;
        } else {
            platforms--;
            j++;
        }
    }
    return maxNeeded;
}

int main() {
    vector<int> arrivals = {900, 1100, 1235, 1300, 1500};
    vector<int> departures = {1000, 1200, 1240, 1320, 1800};
    cout << "Platforms needed: " << minimumPlatforms(arrivals, departures) << endl;
    return 0;
}
