// Stepsort · Job Sequencing
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/job-sequencing

#include <bits/stdc++.h>
using namespace std;

struct Job {
    string id;
    int deadline;
    int profit;
};

pair<vector<string>, int> jobSequencing(vector<Job> jobs) {
    sort(jobs.begin(), jobs.end(),
         [](const Job& a, const Job& b) { return a.profit > b.profit; });
    int maxDeadline = 0;
    for (const Job& job : jobs) maxDeadline = max(maxDeadline, job.deadline);

    vector<string> slots(maxDeadline + 1, "");
    int totalProfit = 0;

    // place each job in the latest free slot before its deadline
    for (const Job& job : jobs) {
        for (int slot = min(job.deadline, maxDeadline); slot >= 1; slot--) {
            if (slots[slot].empty()) {
                slots[slot] = job.id;
                totalProfit += job.profit;
                break;
            }
        }
    }

    vector<string> scheduled;
    for (int i = 1; i <= maxDeadline; i++) {
        if (!slots[i].empty()) scheduled.push_back(slots[i]);
    }
    return {scheduled, totalProfit};
}

int main() {
    vector<Job> jobs = {
        {"J1", 4, 70}, {"J2", 2, 60}, {"J3", 4, 50}, {"J4", 3, 40}, {"J5", 1, 30},
    };
    auto result = jobSequencing(jobs);
    cout << "Scheduled jobs:";
    for (const string& id : result.first) cout << " " << id;
    cout << endl;
    cout << "Total profit: " << result.second << endl;
    return 0;
}
