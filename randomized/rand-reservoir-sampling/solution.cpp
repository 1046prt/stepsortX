// Stepsort · Reservoir Sampling
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-reservoir-sampling

#include <bits/stdc++.h>
using namespace std;

// Sample k items uniformly from a stream of unknown length.
vector<int> reservoirSample(int streamLength, int k, mt19937& rng) {
    vector<int> reservoir;
    reservoir.reserve(k);
    for (int index = 0; index < streamLength; index++) {
        int item = index + 1;  // stand-in for the arriving stream element
        if (index < k) {
            reservoir.push_back(item);
        } else {
            uniform_int_distribution<int> pick(0, index);
            int slot = pick(rng);
            if (slot < k) reservoir[slot] = item;
        }
    }
    return reservoir;
}

int main() {
    mt19937 rng(42);
    vector<int> sample = reservoirSample(20, 3, rng);
    sort(sample.begin(), sample.end());
    cout << "sample of 3 from stream of 20:";
    for (int value : sample) cout << " " << value;
    cout << endl;
    return 0;
}
