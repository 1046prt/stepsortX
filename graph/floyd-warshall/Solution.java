// Stepsort · Floyd-Warshall
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/floyd-warshall

public class Main {
    static final long INF = Long.MAX_VALUE / 2;

    // dist[i][j] becomes the shortest path from i to j
    static void floydWarshall(long[][] dist) {
        int n = dist.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Directed weighted graph, INF = no direct edge
        long[][] dist = {
            {0, 3, INF, 7},
            {8, 0, 2, INF},
            {5, INF, 0, 1},
            {2, INF, INF, 0},
        };

        floydWarshall(dist);

        System.out.println("All-pairs shortest paths:");
        for (long[] row : dist) {
            StringBuilder line = new StringBuilder();
            for (long value : row) {
                line.append(value >= INF ? "  INF" : ("  " + value));
            }
            System.out.println(line.toString());
        }
    }
}
