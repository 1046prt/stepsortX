// Stepsort · Sieve of Eratosthenes
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sieve-eratosthenes

#include <bits/stdc++.h>
using namespace std;

vector<int> sieveOfEratosthenes(int limit) {
    vector<int> primes;
    if (limit < 2) return primes;
    vector<bool> isComposite(limit + 1, false);
    for (int p = 2; p * p <= limit; ++p) {
        if (!isComposite[p]) {
            for (int multiple = p * p; multiple <= limit; multiple += p) {
                isComposite[multiple] = true;
            }
        }
    }
    for (int i = 2; i <= limit; ++i) {
        if (!isComposite[i]) primes.push_back(i);
    }
    return primes;
}

int main() {
    vector<int> primes = sieveOfEratosthenes(50);
    cout << "Primes up to 50:";
    for (int p : primes) cout << " " << p;
    cout << endl;
    cout << "Count: " << primes.size() << endl;
    cout << "Largest: " << primes.back() << endl;
    return 0;
}
