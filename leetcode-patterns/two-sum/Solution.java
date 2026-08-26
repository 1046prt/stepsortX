// Stepsort · Two Sum
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-sum

import java.util.HashMap;
import java.util.Map;

public class Main {
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) return new int[] {seen.get(need), i};
            seen.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] res = twoSum(new int[] {2, 7, 11, 15}, 9);
        System.out.println(res[0] + " " + res[1]);
    }
}
