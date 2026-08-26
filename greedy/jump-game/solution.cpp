// Stepsort · Jump Game
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/jump-game

#include <bits/stdc++.h>
using namespace std;

// greedy: track the farthest index reachable so far
bool canJump(vector<int>& nums) {
    int farthest = 0;
    for (int i = 0; i < (int)nums.size(); i++) {
        if (i > farthest) return false;
        farthest = max(farthest, i + nums[i]);
        if (farthest >= (int)nums.size() - 1) return true;
    }
    return true;
}

int main() {
    vector<int> reachable = {2, 3, 1, 1, 4};
    vector<int> blocked = {3, 2, 1, 0, 4};
    cout << boolalpha << canJump(reachable) << endl;
    cout << boolalpha << canJump(blocked) << endl;
    return 0;
}
