// sortsort · Subsets (Power Set)
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subsets

import java.util.*;

public class Main {
    private static void backtrack(int[] nums, int start, List<Integer> current,
                                  List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // every prefix is a valid subset
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}
