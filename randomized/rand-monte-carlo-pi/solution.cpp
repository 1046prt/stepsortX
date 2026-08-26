// sortsort · Monte Carlo (π)
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-monte-carlo-pi

#include <bits/stdc++.h>
using namespace std;

// Throw darts at the unit square; count hits inside the quarter circle.
double estimatePi(unsigned long long samples, mt19937& rng) {
    uniform_real_distribution<double> unit(0.0, 1.0);
    unsigned long long inside = 0;
    for (unsigned long long i = 0; i < samples; i++) {
        double x = unit(rng);
        double y = unit(rng);
        if (x * x + y * y <= 1.0) inside++;
    }
    return 4.0 * static_cast<double>(inside) / static_cast<double>(samples);
}

int main() {
    mt19937 rng(42);
    cout << fixed << setprecision(6);
    vector<unsigned long long> sizes = {1000ULL, 100000ULL, 1000000ULL};
    for (unsigned long long n : sizes) {
        double estimate = estimatePi(n, rng);
        double error = abs(estimate - 3.141592653589793);
        cout << "samples: " << n << " estimate: " << estimate
             << " error: " << error << endl;
    }
    return 0;
}
