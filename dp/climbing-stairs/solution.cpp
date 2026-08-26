// sortsort · Climbing Stairs
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/climbing-stairs

#include <bits/stdc++.h>
using namespace std;

long long climbStairs(long long n) {
    // Ways(n) follows Fibonacci; O(1) space iterative.
    long long prev = 1, curr = 1;
    for (long long i = 0; i < n - 1; i++) {
        long long next = prev + curr;
        prev = curr;
        curr = next;
    }
    return curr;
}

int main() {
    cout << climbStairs(10) << endl; // 89
}
