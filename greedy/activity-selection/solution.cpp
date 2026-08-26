// sortsort · Activity Selection
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/activity-selection

#include <bits/stdc++.h>
using namespace std;

struct Activity {
    string name;
    int start;
    int finish;
};

vector<string> activitySelection(vector<Activity> activities) {
    // greedy: always take the activity that finishes first
    sort(activities.begin(), activities.end(),
         [](const Activity& a, const Activity& b) { return a.finish < b.finish; });
    vector<string> selected;
    int lastFinish = 0;
    for (const Activity& act : activities) {
        if (selected.empty() || act.start >= lastFinish) {
            selected.push_back(act.name);
            lastFinish = act.finish;
        }
    }
    return selected;
}

int main() {
    vector<Activity> activities = {
        {"A1", 1, 4}, {"A2", 3, 5}, {"A3", 0, 6},
        {"A4", 5, 7}, {"A5", 3, 9}, {"A6", 5, 8},
    };
    vector<string> chosen = activitySelection(activities);
    cout << "Selected activities:";
    for (const string& name : chosen) cout << " " << name;
    cout << endl;
    cout << "Maximum count: " << chosen.size() << endl;
    return 0;
}
