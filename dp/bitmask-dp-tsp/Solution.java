// sortsort · Bitmask DP (TSP)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bitmask-dp-tsp

public class Main {
    public static void main(String[] args) {
        int[][] dist = {{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};
        int n = 4, FULL = (1 << n) - 1;
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[1][0] = 0;
        for (int mask = 1; mask <= FULL; mask += 2)
            for (int last = 0; last < n; last++) {
                if ((mask & (1 << last)) == 0 || dp[mask][last] == Integer.MAX_VALUE) continue;
                for (int nxt = 0; nxt < n; nxt++) {
                    if ((mask & (1 << nxt)) != 0) continue;
                    int nm = mask | (1 << nxt);
                    dp[nm][nxt] = Math.min(dp[nm][nxt], dp[mask][last] + dist[last][nxt]);
                }
            }
        int best = Integer.MAX_VALUE;
        for (int last = 1; last < n; last++)
            best = Math.min(best, dp[FULL][last] + dist[last][0]);
        System.out.println("optimal tour cost: " + best);   // 80
    }
}
