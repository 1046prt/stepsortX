// Stepsort · Find Median from Data Stream
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/find-median-data-stream

#include <bits/stdc++.h>
using namespace std;

class MedianFinder {
    priority_queue<long long> small;                                        // max heap
    priority_queue<long long, vector<long long>, greater<long long>> large; // min heap

public:
    void addNum(long long num) {
        small.push(num);
        large.push(small.top());
        small.pop();
        // keep small >= large in size
        if (large.size() > small.size()) {
            small.push(large.top());
            large.pop();
        }
    }

    double findMedian() const {
        if (small.size() > large.size()) return small.top();
        return ((double)small.top() + (double)large.top()) / 2.0;
    }
};

int main() {
    MedianFinder finder;
    for (long long x : {5, 15, 1, 3}) {
        finder.addNum(x);
        cout << "added " << x << " -> median " << finder.findMedian() << endl;
    }
}
