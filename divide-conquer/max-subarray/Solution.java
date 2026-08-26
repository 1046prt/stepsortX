// sortsort · Maximum Subarray
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/max-subarray

public class Main {

    // result holds: [0] best sum, [1] start index, [2] end index
    static void maxSubarray(int[] nums, int[] result) {
        int best = nums[0], current = nums[0];
        int start = 0, end = 0, tempStart = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > current + nums[i]) {
                current = nums[i];
                tempStart = i;
            } else {
                current += nums[i];
            }
            if (current > best) {
                best = current;
                start = tempStart;
                end = i;
            }
        }
        result[0] = best;
        result[1] = start;
        result[2] = end;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] r = new int[3];
        maxSubarray(nums, r);
        System.out.println("max sum: " + r[0]);
        StringBuilder sb = new StringBuilder("subarray:");
        for (int i = r[1]; i <= r[2]; i++) sb.append(" ").append(nums[i]);
        System.out.println(sb.toString());
        System.out.println("range: indices " + r[1] + " to " + r[2]);
    }
}
