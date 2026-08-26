// sortsort · Las Vegas Sort
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-las-vegas-sort

#include <bits/stdc++.h>
using namespace std;

bool isSorted(const vector<int>& a) {
    for (size_t i = 1; i < a.size(); i++)
        if (a[i - 1] > a[i]) return false;
    return true;
}

void shuffle(vector<int>& a, mt19937& rng) {
    for (int i = static_cast<int>(a.size()) - 1; i > 0; i--) {
        uniform_int_distribution<int> pick(0, i);
        swap(a[i], a[pick(rng)]);
    }
}

// Las Vegas scheme: running time is random, correctness is guaranteed
// because we verify the permutation before returning it.
pair<vector<int>, int> lasVegasSort(vector<int> a, mt19937& rng) {
    int attempts = 0;
    while (!isSorted(a)) {
        shuffle(a, rng);
        attempts++;
    }
    return make_pair(a, attempts);
}

int main() {
    mt19937 rng(42);
    vector<int> data = {5, 2, 9, 1, 7};
    pair<vector<int>, int> outcome = lasVegasSort(data, rng);
    cout << "attempts needed: " << outcome.second << endl;
    cout << "sorted:";
    for (int value : outcome.first) cout << " " << value;
    cout << endl;
    return 0;
}
