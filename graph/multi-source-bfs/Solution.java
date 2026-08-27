// Stepsort · Multi-Source BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-source-bfs

import java.util.*;

public class MultiSourceBFS {
    static int[][] multiSourceBFS(int[][] grid, int[][] sources) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, -1);
        Queue<int[]> queue = new LinkedList<>();
        for (int[] s : sources) { dist[s[0]][s[1]] = 0; queue.add(s); }
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dirs) {
                int nr = cur[0]+d[0], nc = cur[1]+d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] grid = new int[3][4];
        int[][] sources = {{0,0},{2,3}};
        int[][] dist = multiSourceBFS(grid, sources);
        for (int[] row : dist) System.out.println(Arrays.toString(row));
    }
}
