// sortsort · House Robber
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/house-robber

public class Main {
    // Max loot when adjacent houses cannot both be robbed,
    // using O(1) rolling variables
    static int rob(int[] houses) {
        int prev2 = 0, prev1 = 0;  // best up to house i-2 and i-1
        for (int money : houses) {
            int best = Math.max(prev1, prev2 + money);
            prev2 = prev1;
            prev1 = best;
        }
        return prev1;
    }

    public static void main(String[] args) {
        int[][] streets = {{2, 7, 9, 3, 1}, {1, 2, 3, 1}};
        for (int[] street : streets) {
            StringBuilder line = new StringBuilder("Houses:");
            for (int h : street) line.append(" ").append(h);
            System.out.println(line.toString() + " -> max loot: " + rob(street));
        }
    }
}
