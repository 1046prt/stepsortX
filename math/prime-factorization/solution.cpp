// sortsort · Prime Factorization
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/prime-factorization

#include <bits/stdc++.h>
using namespace std;

vector<long long> primeFactors(long long n) {
    vector<long long> factors;
    for (long long d = 2; d * d <= n; ++d) {
        while (n % d == 0) {
            factors.push_back(d);
            n /= d;
        }
    }
    if (n > 1) factors.push_back(n);
    return factors;
}

vector<pair<long long, int>> groupedFactors(long long n) {
    vector<pair<long long, int>> groups;
    for (long long d = 2; d * d <= n; ++d) {
        if (n % d == 0) {
            int e = 0;
            while (n % d == 0) {
                n /= d;
                ++e;
            }
            groups.push_back({d, e});
        }
    }
    if (n > 1) groups.push_back({n, 1});
    return groups;
}

void printList(const vector<long long>& factors) {
    for (size_t i = 0; i < factors.size(); ++i) {
        if (i > 0) cout << " x ";
        cout << factors[i];
    }
}

int main() {
    vector<long long> values = {60, 100, 97, 360, 1024};
    for (long long v : values) {
        cout << v << " = ";
        printList(primeFactors(v));
        cout << "   grouped:";
        for (const auto& pe : groupedFactors(v)) {
            cout << " (" << pe.first << "^" << pe.second << ")";
        }
        cout << endl;
    }
    return 0;
}
