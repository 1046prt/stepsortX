// Stepsort · Catalan Numbers
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/catalan-numbers

#include <bits/stdc++.h>
using namespace std;

vector<unsigned long long> catalanDp(int count) {
    vector<unsigned long long> cat(max(count, 1), 0ULL);
    cat[0] = 1;
    for (int i = 1; i < count; ++i) {
        for (int j = 0; j < i; ++j) {
            cat[i] += cat[j] * cat[i - 1 - j];
        }
    }
    return cat;
}

unsigned long long binomial(int n, int k) {
    unsigned long long result = 1;
    for (int i = 0; i < k; ++i) {
        result = result * (n - i) / (i + 1);
    }
    return result;
}

unsigned long long catalanClosedForm(int n) {
    // nth Catalan number = C(2n, n) / (n + 1)
    return binomial(2 * n, n) / (n + 1);
}

int main() {
    vector<unsigned long long> cat = catalanDp(10);
    cout << "First 10 Catalan numbers:";
    for (unsigned long long c : cat) cout << " " << c;
    cout << endl;
    bool ok = true;
    for (int i = 0; i < 10; ++i) {
        if (catalanClosedForm(i) != cat[i]) ok = false;
    }
    cout << "closed form matches: " << boolalpha << ok << endl;
    return 0;
}
