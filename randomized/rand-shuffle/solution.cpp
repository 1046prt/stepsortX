// Stepsort · Fisher-Yates Shuffle
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-shuffle

#include <bits/stdc++.h>
using namespace std;

// In-place, unbiased: each of the n! orders equally likely.
void fisherYatesShuffle(vector<int>& a, mt19937& rng) {
    for (int i = static_cast<int>(a.size()) - 1; i > 0; i--) {
        uniform_int_distribution<int> pick(0, i);
        swap(a[i], a[pick(rng)]);
    }
}

void show(const string& label, const vector<int>& a) {
    cout << label << ":";
    for (int value : a) cout << " " << value;
    cout << endl;
}

int main() {
    vector<int> original = {1, 2, 3, 4, 5, 6, 7, 8};
    show("original:", original);

    mt19937 rngA(1234);
    vector<int> firstRun = original;
    fisherYatesShuffle(firstRun, rngA);
    show("shuffle with seed 1234:", firstRun);

    mt19937 rngB(9876);
    vector<int> secondRun = original;
    fisherYatesShuffle(secondRun, rngB);
    show("shuffle with seed 9876:", secondRun);
    return 0;
}
