// sortsort · Gas Station
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gas-station

#include <bits/stdc++.h>
using namespace std;

int gasStationStart(const vector<int>& gas, const vector<int>& cost) {
    // feasible only if the circuit has enough gas overall
    long long totalGas = accumulate(gas.begin(), gas.end(), 0LL);
    long long totalCost = accumulate(cost.begin(), cost.end(), 0LL);
    if (totalGas < totalCost) return -1;

    int tank = 0;
    int start = 0;
    for (int i = 0; i < (int)gas.size(); i++) {
        tank += gas[i] - cost[i];
        // any station reached with negative tank cannot be the start
        if (tank < 0) {
            start = i + 1;
            tank = 0;
        }
    }
    return start;
}

int main() {
    vector<int> gas = {1, 2, 3, 4, 5};
    vector<int> cost = {3, 4, 5, 1, 2};
    cout << "Start station index: " << gasStationStart(gas, cost) << endl;
    return 0;
}
