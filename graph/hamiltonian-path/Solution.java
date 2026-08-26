// Stepsort · Hamiltonian Path
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamiltonian-path

import java.util.Arrays;

public class Main {
    static int[][] adjMat;
    static int n;
    static int[] currentPath;
    static boolean[] visited;

    // Try to extend the path so every vertex appears exactly once
    static boolean backtrack(int placed, int last) {
        if (placed == n) return true;
        for (int v = 0; v < n; v++) {
            if (visited[v]) continue;
            if (placed > 0 && adjMat[last][v] == 0) continue;  // must extend the path
            visited[v] = true;
            currentPath[placed] = v;
            if (backtrack(placed + 1, v)) return true;
            visited[v] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        adjMat = new int[][]{
            {0, 1, 0, 1},
            {1, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 1, 1, 0}
        };
        n = adjMat.length;
        visited = new boolean[n];
        currentPath = new int[n];
        if (backtrack(0, -1)) {
            System.out.println("Hamiltonian path: " + Arrays.toString(currentPath));
        } else {
            System.out.println("No Hamiltonian path exists");
        }
    }
}
