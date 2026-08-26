// sortsort · Subset Sum
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-sum

#include <bits/stdc++.h>
using namespace std;

vector<int> numbers = {3, 34, 4, 12, 5, 2};
int target = 9;

bool findSubset(size_t i, int remaining, vector<int>& current, vector<int>& answer) {
    if (remaining == 0) {
        answer = current;
        return true;
    }
    if (i >= numbers.size() || remaining < 0) return false;
    // try including numbers[i]
    current.push_back(numbers[i]);
    if (findSubset(i + 1, remaining - numbers[i], current, answer)) return true;
    current.pop_back();
    // try excluding numbers[i]
    return findSubset(i + 1, remaining, current, answer);
}

int main() {
    vector<int> current, answer;
    if (findSubset(0, target, current, answer)) {
        cout << "Subset:";
        for (int x : answer) cout << " " << x;
        cout << endl;
    } else {
        cout << "No subset sums to " << target << endl;
    }
    return 0;
}
