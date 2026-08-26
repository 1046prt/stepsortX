// Stepsort · Fibonacci (Matrix Exp)
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-matrix

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef array<array<ll, 2>, 2> Mat;

Mat mult(const Mat& a, const Mat& b) {
    Mat c;
    for (int i = 0; i < 2; ++i) {
        for (int j = 0; j < 2; ++j) {
            c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
        }
    }
    return c;
}

Mat mat_power(Mat m, ll p) {
    Mat result = {{{1, 0}, {0, 1}}};
    while (p > 0) {
        if (p & 1) result = mult(result, m);
        m = mult(m, m);
        p >>= 1;
    }
    return result;
}

ll fib(ll n) {
    // F(n) is an off-diagonal entry of [[1, 1], [1, 0]]^n; F(0) = 0
    if (n == 0) return 0;
    Mat base = {{{1, 1}, {1, 0}}};
    return mat_power(base, n)[0][1];
}

int main() {
    cout << "F(0..10):";
    for (int i = 0; i <= 10; ++i) cout << " " << fib(i);
    cout << endl;
    cout << "F(50) = " << fib(50) << endl;
    cout << "F(90) = " << fib(90) << endl;
    return 0;
}
