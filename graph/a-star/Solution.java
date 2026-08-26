// sortsort · A* Search
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/a-star

import java.util.*;

public class Main {
    // Manhattan distance heuristic (admissible on a unit grid)
    static int manhattan(int node, int goal, int cols) {
        int r = node / cols, c = node % cols;
        int gr = goal / cols, gc = goal % cols;
        return Math.abs(r - gr) + Math.abs(c - gc);
    }

    static List<Integer> aStar(int rows, int cols, boolean[][] blocked,
                               int start, int goal) {
        int n = rows * cols;
        int[] gCost = new int[n], parent = new int[n];
        Arrays.fill(gCost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        PriorityQueue<int[]> open =
            new PriorityQueue<>((a, b) -> a[0] - b[0]); // entries: [f, g, id]
        gCost[start] = 0;
        open.add(new int[]{manhattan(start, goal, cols), 0, start});
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};

        while (!open.isEmpty()) {
            int[] top = open.poll();
            int g = top[1], cur = top[2];
            if (g > gCost[cur]) continue;  // stale entry
            if (cur == goal) {
                LinkedList<Integer> path = new LinkedList<>();
                for (int v = goal; v != -1; v = parent[v]) path.addFirst(v);
                return path;
            }
            int r = cur / cols, c = cur % cols;
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (blocked[nr][nc]) continue;
                int nxt = nr * cols + nc, ng = g + 1;
                if (ng < gCost[nxt]) {
                    gCost[nxt] = ng;
                    parent[nxt] = cur;
                    open.add(new int[]{ng + manhattan(nxt, goal, cols), ng, nxt});
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        int rows = 4, cols = 5;
        boolean[][] blocked = new boolean[rows][cols];
        int[][] walls = {{1, 1}, {1, 3}, {2, 2}, {3, 1}};
        for (int[] w : walls) blocked[w[0]][w[1]] = true;

        List<Integer> path = aStar(rows, cols, blocked, 0, 19);
        System.out.println("Path: " + path);
        System.out.println("Cost: " + (path.size() - 1));
    }
}
