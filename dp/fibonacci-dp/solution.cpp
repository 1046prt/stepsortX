// sortsort · Fibonacci
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-dp

#include <bits/stdc++.h>
using namespace std;

// Top-down: recursion plus a memo cache (-1 means not computed yet)
long long fibMemo(int n, vector<long long>& memo) {
    if (n <= 1) return n;
    if (memo[n] != -1) return memo[n];
    memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    return memo[n];
}

// Bottom-up: iterative table fill
long long fibTab(int n) {
    if (n <= 1) return n;
    vector<long long> table(n + 1);
    table[0] = 0;
    table[1] = 1;
    for (int i = 2; i <= n; i++) {
        table[i] = table[i - 1] + table[i - 2];
    }
    return table[n];
}

int main() {
    int n = 10;
    vector<long long> memo(n + 1, -1);
    cout << "F(10) top-down memoized: " << fibMemo(n, memo) << endl;
    cout << "F(10) bottom-up tabulated: " << fibTab(n) << endl;
    return 0;
}
