// Stepsort · Knight's Tour
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knight-tour

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int N = 5;
    static int[][] board = new int[N][N];
    static int[] dr = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    static int degree(int r, int c) {
        // Warnsdorff heuristic: count onward moves for ordering
        int d = 0;
        for (int k = 0; k < 8; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) d++;
        }
        return d;
    }

    static boolean tour(int r, int c, int step) {
        if (step == N * N) return true;
        List<int[]> candidates = new ArrayList<>();
        for (int k = 0; k < 8; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) {
                candidates.add(new int[]{degree(nr, nc), nr, nc});
            }
        }
        candidates.sort((a, b) -> a[0] - b[0]);
        for (int[] t : candidates) {
            board[t[1]][t[2]] = step + 1;
            if (tour(t[1], t[2], step + 1)) return true;
            board[t[1]][t[2]] = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        board[0][0] = 1;
        if (tour(0, 0, 1)) {
            for (int r = 0; r < N; r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < N; c++) sb.append(String.format("%3d", board[r][c]));
                System.out.println(sb.toString());
            }
        } else {
            System.out.println("No tour found");
        }
    }
}
