// sortsort · Crossword Solver
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/crossword-solver

import java.util.ArrayList;
import java.util.List;

public class Main {
    static String[] words = {"hi", "world", "code"};

    static boolean solve(char[][] grid, List<int[]> slots, int idx, boolean[] used) {
        if (idx == slots.size()) return true;
        int r = slots.get(idx)[0];
        int start = slots.get(idx)[1];
        int length = slots.get(idx)[2];
        for (int wi = 0; wi < words.length; wi++) {
            if (!used[wi] && words[wi].length() == length) {
                used[wi] = true;
                for (int j = 0; j < length; j++) grid[r][start + j] = words[wi].charAt(j);
                if (solve(grid, slots, idx + 1, used)) return true;
                for (int j = 0; j < length; j++) grid[r][start + j] = '-';  // undo
                used[wi] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] pattern = {"--+----", "-----+"};
        char[][] grid = new char[pattern.length][];
        List<int[]> slots = new ArrayList<>();
        for (int r = 0; r < pattern.length; r++) {
            grid[r] = pattern[r].toCharArray();
            int c = 0;
            while (c < pattern[r].length()) {
                if (pattern[r].charAt(c) == '-') {
                    int start = c;
                    while (c < pattern[r].length() && pattern[r].charAt(c) == '-') c++;
                    slots.add(new int[]{r, start, c - start});
                } else {
                    c++;
                }
            }
        }
        if (solve(grid, slots, 0, new boolean[words.length])) {
            for (char[] row : grid) {
                System.out.println(new String(row));
            }
        } else {
            System.out.println("No solution");
        }
    }
}
