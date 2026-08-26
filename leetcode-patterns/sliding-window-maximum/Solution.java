// Stepsort · Sliding Window Maximum
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sliding-window-maximum

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();  // indices, values decreasing
        int n = nums.length;
        int[] result = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();
            dq.addLast(i);
            if (dq.peekFirst() == i - k) dq.pollFirst();
            if (i >= k - 1) result[i - k + 1] = nums[dq.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] res = maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int i = 0; i < res.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(res[i]);
        }
        System.out.println();
    }
}
