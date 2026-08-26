// sortsort · Partition Equal Subset Sum
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/partition-equal-subset

public class Main {
    // True iff nums splits into two subsets with equal sums
    static boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        boolean[] reachable = new boolean[target + 1];
        reachable[0] = true;  // empty subset reaches sum 0
        for (int num : nums) {
            // iterate sums downward so each num is used at most once
            for (int s = target; s >= num; s--) {
                if (reachable[s - num]) reachable[s] = true;
            }
        }
        return reachable[target];
    }

    public static void main(String[] args) {
        System.out.println("[1, 5, 11, 5] partitionable: " + canPartition(new int[]{1, 5, 11, 5}));
        System.out.println("[1, 2, 3, 5] partitionable: " + canPartition(new int[]{1, 2, 3, 5}));
    }
}
