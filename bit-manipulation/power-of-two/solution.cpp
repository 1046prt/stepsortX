// sortsort · Power of Two Check
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/power-of-two

#include <bits/stdc++.h>
using namespace std;

bool isPowerOfTwo(long long n) {
    return n > 0 && (n & (n - 1)) == 0;
}

int main() {
    long long tests[] = {0, 1, 2, 3, 16, 31, 64, 100, 128};
    for (long long v : tests) {
        cout << v << " -> " << (isPowerOfTwo(v) ? "true" : "false") << endl;
    }
    return 0;
}
