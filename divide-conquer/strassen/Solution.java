// sortsort · Strassen's Multiplication
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/strassen

public class Main {

    static long[][] add(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    static long[][] sub(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    static long[][] standardMultiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int k = 0; k < n; k++)
                for (int j = 0; j < n; j++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    static long[][] strassen(long[][] A, long[][] B) {
        int n = A.length;
        if (n <= 2) return standardMultiply(A, B);
        int h = n / 2;
        long[][] a11 = new long[h][h], a12 = new long[h][h],
                 a21 = new long[h][h], a22 = new long[h][h],
                 b11 = new long[h][h], b12 = new long[h][h],
                 b21 = new long[h][h], b22 = new long[h][h];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < h; j++) {
                a11[i][j] = A[i][j];
                a12[i][j] = A[i][j + h];
                a21[i][j] = A[i + h][j];
                a22[i][j] = A[i + h][j + h];
                b11[i][j] = B[i][j];
                b12[i][j] = B[i][j + h];
                b21[i][j] = B[i + h][j];
                b22[i][j] = B[i + h][j + h];
            }
        long[][] m1 = strassen(add(a11, a22), add(b11, b22));
        long[][] m2 = strassen(add(a21, a22), b11);
        long[][] m3 = strassen(a11, sub(b12, b22));
        long[][] m4 = strassen(a22, sub(b21, b11));
        long[][] m5 = strassen(add(a11, a12), b22);
        long[][] m6 = strassen(sub(a21, a11), add(b11, b12));
        long[][] m7 = strassen(sub(a12, a22), add(b21, b22));
        long[][] c11 = add(sub(add(m1, m4), m5), m7);
        long[][] c12 = add(m3, m5);
        long[][] c21 = add(m2, m4);
        long[][] c22 = add(sub(add(m1, m3), m2), m6);
        long[][] C = new long[n][n];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < h; j++) {
                C[i][j] = c11[i][j];
                C[i][j + h] = c12[i][j];
                C[i + h][j] = c21[i][j];
                C[i + h][j + h] = c22[i][j];
            }
        return C;
    }

    public static void main(String[] args) {
        long[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        long[][] B = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}};
        long[][] C = strassen(A, B);
        System.out.println("A x B:");
        for (long[] row : C) {
            StringBuilder sb = new StringBuilder();
            for (long v : row) sb.append(" ").append(v);
            System.out.println(sb.toString());
        }
    }
}
