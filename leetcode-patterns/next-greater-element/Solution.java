// Stepsort · Next Greater Element
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/next-greater-element

import java.util.*;

public class NextGreaterElement {
    static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
            if (!stack.isEmpty()) result[i] = stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 5, 2, 25})));
    }
}
