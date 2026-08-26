// Stepsort · Reverse Bits
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-reverse

#include <bits/stdc++.h>
using namespace std;

uint32_t reverseBits(uint32_t n) {
    uint32_t result = 0;
    for (int i = 0; i < 32; i++) {
        result = (result << 1) | (n & 1u);
        n >>= 1;
    }
    return result;
}

int main() {
    uint32_t samples[] = {1u, 43261596u, 4294967280u};
    for (uint32_t v : samples) {
        cout << v << " -> " << reverseBits(v) << endl;
    }
    return 0;
}
