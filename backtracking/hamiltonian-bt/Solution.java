// sortsort · Hamiltonian Cycle (BT)
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamiltonian-bt

public class Main {
    static final int V = 5;
    static int[][] graph = {
        {0, 1, 0, 1, 0},
        {1, 0, 1, 1, 1},
        {0, 1, 0, 0, 1},
        {1, 1, 0, 0, 1},
        {0, 1, 1, 1, 0}
    };

    static boolean isSafe(int v, int[] path, int pos) {
        if (graph[path[pos - 1]][v] == 0) return false;
        for (int u : path) {
            if (u == v) return false;
        }
        return true;
    }

    static boolean hamCycle(int[] path, int pos) {
        if (pos == V) return graph[path[pos - 1]][path[0]] == 1;
        for (int v = 1; v < V; v++) {
            if (isSafe(v, path, pos)) {
                path[pos] = v;
                if (hamCycle(path, pos + 1)) return true;
                path[pos] = -1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] path = new int[V];
        java.util.Arrays.fill(path, -1);
        path[0] = 0;
        if (hamCycle(path, 1)) {
            StringBuilder sb = new StringBuilder("Hamiltonian cycle:");
            for (int v : path) sb.append(" ").append(v);
            sb.append(" ").append(path[0]);
            System.out.println(sb.toString());
        } else {
            System.out.println("No Hamiltonian cycle exists");
        }
    }
}
