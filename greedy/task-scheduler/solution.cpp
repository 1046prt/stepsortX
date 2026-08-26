// Stepsort · Task Scheduler
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/task-scheduler

#include <bits/stdc++.h>
using namespace std;

int leastInterval(const vector<char>& tasks, int cooldown) {
    // idle units are bounded by the most frequent task
    array<int, 26> freq{};
    for (char t : tasks) freq[t - 'A']++;

    int maxFreq = *max_element(freq.begin(), freq.end());
    int countMax = (int)count(freq.begin(), freq.end(), maxFreq);
    return max((int)tasks.size(), (maxFreq - 1) * (cooldown + 1) + countMax);
}

int main() {
    vector<char> tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
    vector<char> tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
    cout << "Minimum units: " << leastInterval(tasks1, 2) << endl;
    cout << "Minimum units: " << leastInterval(tasks2, 1) << endl;
    return 0;
}
