// sortsort · Egg Drop Problem
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/egg-drop

public class Main {
    // dp[e][f] = minimum trials needed with e eggs and f floors
    static int eggDrop(int eggs, int floors) {
        long[][] dp = new long[eggs + 1][floors + 1];
        for (int f = 1; f <= floors; f++) dp[1][f] = f;
        for (int e = 2; e <= eggs; e++) {
            for (int f = 1; f <= floors; f++) {
                long best = Long.MAX_VALUE;
                for (int x = 1; x <= f; x++) {  // drop from floor x
                    long worst = Math.max(dp[e - 1][x - 1], dp[e][f - x]);
                    best = Math.min(best, 1 + worst);
                }
                dp[e][f] = best;
            }
        }
        return (int) dp[eggs][floors];
    }

    public static void main(String[] args) {
        System.out.println("Egg drop with 2 eggs, 10 floors: " + eggDrop(2, 10));
    }
}
