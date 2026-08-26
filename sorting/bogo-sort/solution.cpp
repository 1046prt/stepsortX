// sortsort · Bogo Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bogo-sort

#include <bits/stdc++.h>
using namespace std;

// Fixed-seed linear congruential generator so demos always terminate.
struct DetRng {
    long long state;
    explicit DetRng(long long seed) : state(seed) {}
    int below(int bound) {
        state = (state * 1103515245LL + 12345LL) % 2147483648LL;
        return (int)((state >> 8) % bound);
    }
};

bool isSorted(const vector<int>& arr) {
    for (size_t i = 1; i < arr.size(); i++)
        if (arr[i - 1] > arr[i]) return false;
    return true;
}

void fisherYates(vector<int>& arr, DetRng& rng) {
    for (int i = (int)arr.size() - 1; i > 0; i--)
        swap(arr[i], arr[rng.below(i + 1)]);
}

int bogoSort(vector<int>& arr) {
    DetRng rng(20240817LL);
    int attempts = 0;
    while (!isSorted(arr)) {
        fisherYates(arr, rng);
        attempts++;
    }
    return attempts;
}

int main() {
    vector<int> data = {4, 1, 3, 2};
    cout << "attempts: " << bogoSort(data) << endl;
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
