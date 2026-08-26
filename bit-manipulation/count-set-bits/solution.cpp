// Stepsort · Count Set Bits
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-set-bits

#include <bits/stdc++.h>
using namespace std;

// Brian Kernighan: n & (n - 1) clears the lowest set bit
int countSetBits(unsigned int n) {
    int count = 0;
    while (n) {
        n &= n - 1;
        count++;
    }
    return count;
}

int main() {
    vector<unsigned int> values = {0u, 1u, 7u, 13u, 255u, 1023u};
    for (unsigned int v : values) {
        cout << v << ": kernighan=" << countSetBits(v)
             << " builtin=" << __builtin_popcount(v) << endl;
    }
    return 0;
}
