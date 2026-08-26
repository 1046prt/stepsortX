// sortsort · Hamming Distance
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamming-distance

#include <bits/stdc++.h>
using namespace std;

int hammingDistance(unsigned int x, unsigned int y) {
    unsigned int diff = x ^ y;
    int count = 0;
    while (diff) {
        diff &= diff - 1;
        count++;
    }
    return count;
}

int main() {
    vector<pair<unsigned int, unsigned int>> pairs = {
        {1u, 4u}, {3u, 1u}, {0u, 255u}, {93u, 73u}};
    for (const auto& p : pairs) {
        cout << p.first << " vs " << p.second << " -> "
             << hammingDistance(p.first, p.second) << endl;
    }
    return 0;
}
