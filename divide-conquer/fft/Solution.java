// Stepsort · FFT (Fast Fourier Transform)
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fft

public class Main {

    static class Complex {
        double re, im;

        Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        Complex plus(Complex o) { return new Complex(re + o.re, im + o.im); }
        Complex minus(Complex o) { return new Complex(re - o.re, im - o.im); }
        Complex times(Complex o) {
            return new Complex(re * o.re - im * o.im, re * o.im + im * o.re);
        }
    }

    // iterative radix-2 Cooley-Tukey transform, done in place
    static void fft(Complex[] a, boolean invert) {
        int n = a.length;
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            for (; (j & bit) != 0; bit >>= 1) j ^= bit;
            j |= bit;
            if (i < j) {
                Complex t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        for (int length = 2; length <= n; length <<= 1) {
            double sign = invert ? 1 : -1;
            double ang = sign * 2 * Math.PI / length;
            Complex wLen = new Complex(Math.cos(ang), Math.sin(ang));
            for (int start = 0; start < n; start += length) {
                Complex w = new Complex(1, 0);
                for (int k = 0; k < length / 2; k++) {
                    Complex u = a[start + k];
                    Complex v = a[start + k + length / 2].times(w);
                    a[start + k] = u.plus(v);
                    a[start + k + length / 2] = u.minus(v);
                    w = w.times(wLen);
                }
            }
        }
        if (invert)
            for (int i = 0; i < n; i++) a[i] = new Complex(a[i].re / n, a[i].im / n);
    }

    static long[] multiplyPoly(int[] p, int[] q) {
        int need = p.length + q.length - 1;
        int size = 1;
        while (size < need) size <<= 1;
        Complex[] fa = new Complex[size];
        Complex[] fb = new Complex[size];
        for (int i = 0; i < size; i++) {
            fa[i] = new Complex(i < p.length ? p[i] : 0, 0);
            fb[i] = new Complex(i < q.length ? q[i] : 0, 0);
        }
        fft(fa, false);
        fft(fb, false);
        for (int i = 0; i < size; i++) fa[i] = fa[i].times(fb[i]);
        fft(fa, true);
        long[] result = new long[need];
        for (int i = 0; i < need; i++) result[i] = Math.round(fa[i].re);
        return result;
    }

    public static void main(String[] args) {
        int[] p = {1, 2, 3};
        int[] q = {4, 5};
        System.out.println("p coefficients: " + java.util.Arrays.toString(p));
        System.out.println("q coefficients: " + java.util.Arrays.toString(q));
        StringBuilder sb = new StringBuilder("product coefficients:");
        for (long c : multiplyPoly(p, q)) sb.append(" ").append(c);
        System.out.println(sb.toString());
    }
}
