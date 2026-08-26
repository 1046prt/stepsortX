// Stepsort · M-Coloring Problem
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/graph-coloring-bt

public class Main {
    static final int V = 4;
    static int[][] graph = {
        {0, 1, 0, 1},
        {1, 0, 1, 0},
        {0, 1, 0, 1},
        {1, 0, 1, 0}
    };
    static int m = 3;
    static int[] colors = new int[V];

    static boolean isSafe(int v, int c) {
        for (int u = 0; u < V; u++) {
            if (graph[v][u] == 1 && colors[u] == c) return false;
        }
        return true;
    }

    static boolean colorGraph(int v) {
        if (v == V) return true;
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                colors[v] = c;
                if (colorGraph(v + 1)) return true;
                colors[v] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (colorGraph(0)) {
            StringBuilder sb = new StringBuilder("Color assignment:");
            for (int v = 0; v < V; v++) sb.append(" ").append(colors[v]);
            System.out.println(sb.toString());
        } else {
            System.out.println("Not possible with " + m + " colors");
        }
    }
}
