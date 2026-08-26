// Stepsort · Missing Number (XOR)
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/missing-number-bit

public class Main {
    static int missingNumber(int[] nums) {
        int n = nums.length;
        int result = 0;
        for (int v = 0; v <= n; v++) {
            result ^= v;
        }
        for (int v : nums) {
            result ^= v;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4};
        System.out.println("missing: " + missingNumber(nums));
    }
}
