// Stepsort · Flood Fill
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/flood-fill

public class FloodFill {
    static void floodFill(int[][] grid, int sr, int sc, int newColor) {
        int rows = grid.length, cols = grid[0].length;
        int original = grid[sr][sc];
        if (original == newColor) return;
        dfs(grid, sr, sc, rows, cols, original, newColor);
    }

    static void dfs(int[][] grid, int r, int c, int rows, int cols, int original, int newColor) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return;
        if (grid[r][c] != original) return;
        grid[r][c] = newColor;
        dfs(grid, r+1, c, rows, cols, original, newColor);
        dfs(grid, r-1, c, rows, cols, original, newColor);
        dfs(grid, r, c+1, rows, cols, original, newColor);
        dfs(grid, r, c-1, rows, cols, original, newColor);
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1},{1,1,0},{1,0,1}};
        floodFill(grid, 1, 1, 2);
        for (int[] row : grid) System.out.println(java.util.Arrays.toString(row));
    }
}
