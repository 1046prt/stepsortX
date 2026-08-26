// Stepsort · Ford-Fulkerson
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-ford-fulkerson

public class Main {
    static int dfs(int[][] residual, boolean[] visited, int u, int t, int flow) {
        if (u == t) return flow;
        visited[u] = true;
        for (int v = 0; v < residual.length; v++) {
            if (!visited[v] && residual[u][v] > 0) {
                int pushed = dfs(residual, visited, v, t, Math.min(flow, residual[u][v]));
                if (pushed > 0) {
                    residual[u][v] -= pushed;
                    residual[v][u] += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }

    static int fordFulkerson(int n, int[][] residual, int s, int t) {
        int maxFlow = 0;
        while (true) {
            int pushed = dfs(residual, new boolean[n], s, t, Integer.MAX_VALUE);
            if (pushed == 0) break;
            maxFlow += pushed;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] capacity = new int[n][n];
        int[][] edges = {
            {0, 1, 16}, {0, 2, 13},
            {1, 3, 12},
            {2, 1, 4}, {2, 4, 14},
            {3, 2, 9}, {3, 5, 20},
            {4, 3, 7}, {4, 5, 4}
        };
        for (int[] e : edges) capacity[e[0]][e[1]] = e[2];
        System.out.println("Max flow: " + fordFulkerson(n, capacity, 0, 5));
    }
}
