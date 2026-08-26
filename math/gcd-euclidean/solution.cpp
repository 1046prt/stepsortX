// sortsort · Euclidean GCD
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gcd-euclidean

#include <bits/stdc++.h>
using namespace std;

long long gcdIterative(long long a, long long b) {
    while (b != 0) {
        long long r = a % b;
        a = b;
        b = r;
    }
    return a < 0 ? -a : a;
}

long long gcdRecursive(long long a, long long b) {
    if (b == 0) return a < 0 ? -a : a;
    return gcdRecursive(b, a % b);
}

long long lcmFromGcd(long long a, long long b) {
    return a / gcdIterative(a, b) * b;
}

int main() {
    vector<pair<long long, long long>> pairs =
        {{48, 18}, {100, 75}, {17, 13}, {270, 192}};
    for (const auto& pr : pairs) {
        cout << "gcd(" << pr.first << ", " << pr.second << ") = "
             << gcdIterative(pr.first, pr.second)
             << " (recursive: " << gcdRecursive(pr.first, pr.second)
             << "), lcm = " << lcmFromGcd(pr.first, pr.second) << endl;
    }
    return 0;
}
