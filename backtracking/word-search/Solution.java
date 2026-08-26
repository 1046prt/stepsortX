// Stepsort · Word Search
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/word-search

public class Main {
    static char[][] grid = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'C', 'S'},
        {'A', 'D', 'E', 'E'}
    };

    static boolean dfs(int r, int c, String word, int idx) {
        if (idx == word.length()) return true;
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length
                || grid[r][c] != word.charAt(idx)) {
            return false;
        }
        char saved = grid[r][c];
        grid[r][c] = '#';  // mark visited
        boolean found = dfs(r + 1, c, word, idx + 1) || dfs(r - 1, c, word, idx + 1) ||
                        dfs(r, c + 1, word, idx + 1) || dfs(r, c - 1, word, idx + 1);
        grid[r][c] = saved;  // unmark
        return found;
    }

    static boolean exist(String word) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (dfs(r, c, word, 0)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"ABCCED", "SEE", "ABCB"};
        for (String w : words) {
            System.out.println(w + " -> " + exist(w));
        }
    }
}
