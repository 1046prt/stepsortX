// sortsort · Maximal Square
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/maximal-square

public class Main {
    // dp[i][j] = side of largest square with bottom-right corner at (i-1, j-1)
    static int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int side = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] =
                        Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1;
                    side = Math.max(side, dp[i + 1][j + 1]);
                }
            }
        }
        return side * side;
    }

    public static void main(String[] args) {
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'},
        };
        System.out.println("Largest square area: " + maximalSquare(matrix));
    }
}
