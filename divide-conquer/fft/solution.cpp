// sortsort · FFT (Fast Fourier Transform)
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fft

#include <bits/stdc++.h>
using namespace std;

struct Complex {
    double re, im;
};

Complex operator+(Complex a, Complex b) { return {a.re + b.re, a.im + b.im}; }
Complex operator-(Complex a, Complex b) { return {a.re - b.re, a.im - b.im}; }
Complex operator*(Complex a, Complex b) {
    return {a.re * b.re - a.im * b.im, a.re * b.im + a.im * b.re};
}

// iterative radix-2 Cooley-Tukey transform, done in place
void fft(vector<Complex>& a, bool invert) {
    int n = (int)a.size();
    for (int i = 1, j = 0; i < n; i++) {
        int bit = n >> 1;
        for (; j & bit; bit >>= 1) j ^= bit;
        j |= bit;
        if (i < j) swap(a[i], a[j]);
    }
    for (int length = 2; length <= n; length <<= 1) {
        double sign = invert ? 1 : -1;
        double ang = sign * 2 * acos(-1.0) / length;
        Complex wLen = {cos(ang), sin(ang)};
        for (int start = 0; start < n; start += length) {
            Complex w = {1, 0};
            for (int k = 0; k < length / 2; k++) {
                Complex u = a[start + k];
                Complex v = a[start + k + length / 2] * w;
                a[start + k] = u + v;
                a[start + k + length / 2] = u - v;
                w = w * wLen;
            }
        }
    }
    if (invert)
        for (Complex& z : a) z = {z.re / n, z.im / n};
}

vector<long long> multiplyPoly(const vector<long long>& p, const vector<long long>& q) {
    int need = (int)p.size() + (int)q.size() - 1;
    int size = 1;
    while (size < need) size <<= 1;
    vector<Complex> fa(size, {0, 0}), fb(size, {0, 0});
    for (int i = 0; i < (int)p.size(); i++) fa[i] = {(double)p[i], 0};
    for (int i = 0; i < (int)q.size(); i++) fb[i] = {(double)q[i], 0};
    fft(fa, false);
    fft(fb, false);
    for (int i = 0; i < size; i++) fa[i] = fa[i] * fb[i];
    fft(fa, true);
    vector<long long> result(need);
    for (int i = 0; i < need; i++) result[i] = llround(fa[i].re);
    return result;
}

int main() {
    vector<long long> p = {1, 2, 3};
    vector<long long> q = {4, 5};
    cout << "p coefficients:";
    for (long long c : p) cout << " " << c;
    cout << endl;
    cout << "q coefficients:";
    for (long long c : q) cout << " " << c;
    cout << endl;
    cout << "product coefficients:";
    for (long long c : multiplyPoly(p, q)) cout << " " << c;
    cout << endl;
    return 0;
}
