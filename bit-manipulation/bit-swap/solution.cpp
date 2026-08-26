// Stepsort · Swap Without Temp
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-swap

#include <bits/stdc++.h>
using namespace std;

void xorSwap(int& a, int& b) {
    if (&a == &b) return; // swapping with self would zero the value
    a ^= b;
    b ^= a;
    a ^= b;
}

int main() {
    int x = 3, y = 9;
    cout << "before: " << x << " " << y << endl;
    xorSwap(x, y);
    cout << "after: " << x << " " << y << endl;
    return 0;
}
