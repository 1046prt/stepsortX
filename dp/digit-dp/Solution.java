// sortsort · Digit DP
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/digit-dp

public class Main {
    static String N;
    static Long[][][] memo;

    static long dfs(int pos, int prevDigit, boolean tight, boolean started) {
        if (pos == N.length()) return started ? 1 : 0;
        if (!tight && memo[pos][prevDigit][started ? 1 : 0] != null)
            return memo[pos][prevDigit][started ? 1 : 0];
        int maxD = tight ? N.charAt(pos) - '0' : 9;
        long total = 0;
        for (int d = 0; d <= maxD; d++) {
            if (started && d < prevDigit) continue;
            total += dfs(pos + 1, d, tight && d == maxD, started || d > 0);
        }
        if (!tight) memo[pos][prevDigit][started ? 1 : 0] = total;
        return total;
    }

    public static void main(String[] args) {
        N = "356";
        memo = new Long[20][10][2];
        System.out.println(dfs(0, 0, true, false));   // 84
    }
}
