// Stepsort · Sliding Window Minimum
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sliding-window-minimum

import java.util.*;

public class SlidingWindowMinimum {
    static int[] slidingWindowMinimum(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            while (!dq.isEmpty() && nums[dq.peekLast()] >= nums[i]) dq.pollLast();
            dq.addLast(i);
            if (i >= k - 1) result[i - k + 1] = nums[dq.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(slidingWindowMinimum(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }
}
