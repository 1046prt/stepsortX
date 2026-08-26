// sortsort · N-Queens
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/n-queens

public class Main {
    static final int N = 4;
    static int[] queens = new int[N];

    static boolean safe(int row, int col) {
        for (int r = 0; r < row; r++) {
            if (queens[r] == col || Math.abs(queens[r] - col) == row - r) return false;
        }
        return true;
    }

    static boolean place(int row) {
        if (row == N) return true;
        for (int col = 0; col < N; col++) {
            if (safe(row, col)) {
                queens[row] = col;
                if (place(row + 1)) return true;
                queens[row] = -1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (place(0)) {
            for (int r = 0; r < N; r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < N; c++) sb.append(queens[r] == c ? "Q " : ". ");
                System.out.println(sb.toString());
            }
        } else {
            System.out.println("No solution");
        }
    }
}
