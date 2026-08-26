// sortsort · Binary Search on Answer
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search-on-answer

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> piles = {3, 6, 7, 11};
    int H = 8;
    auto hoursNeeded = [&](long long s) {
        long long t = 0;
        for (int p : piles) t += (p + s - 1) / s;
        return t;
    };
    int lo = 1, hi = *max_element(piles.begin(), piles.end()), ans = hi;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (hoursNeeded(mid) <= H) { ans = mid; hi = mid - 1; }
        else lo = mid + 1;
    }
    cout << "min speed: " << ans << endl;   // 4
}
