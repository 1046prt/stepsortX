// Stepsort · Two Sum (Hash Map)
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-sum-hash

import java.util.HashMap;

public class Main {
    static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = seen.get(target - nums[i]);
            if (j != null) return new int[] {j, i};
            seen.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15, 3, 6};
        int[] targets = {9, 18, 30};
        for (int target : targets) {
            int[] pair = twoSum(nums, target);
            if (pair[0] == -1) {
                System.out.println("target " + target + " -> no pair found");
            } else {
                System.out.println("target " + target + " -> indices "
                        + pair[0] + "," + pair[1] + " values "
                        + nums[pair[0]] + "," + nums[pair[1]]);
            }
        }
    }
}
