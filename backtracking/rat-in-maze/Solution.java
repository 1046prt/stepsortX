// Stepsort · Rat in a Maze
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rat-in-maze

public class Main {
    static final int N = 4;
    static int[][] maze = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {0, 1, 1, 1}
    };
    static int[][] path = new int[N][N];

    static boolean go(int r, int c) {
        if (r == N - 1 && c == N - 1 && maze[r][c] == 1) {
            path[r][c] = 1;
            return true;
        }
        if (r >= 0 && r < N && c >= 0 && c < N && maze[r][c] == 1 && path[r][c] == 0) {
            path[r][c] = 1;
            if (go(r + 1, c) || go(r, c + 1) || go(r - 1, c) || go(r, c - 1)) return true;
            path[r][c] = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        if (go(0, 0)) {
            for (int r = 0; r < N; r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < N; c++) sb.append(path[r][c]).append(" ");
                System.out.println(sb.toString());
            }
        } else {
            System.out.println("No path found");
        }
    }
}
