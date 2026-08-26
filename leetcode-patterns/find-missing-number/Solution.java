// Stepsort · Find Missing Number
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/find-missing-number

public class Main {
    static int missingNumber(int[] nums) {
        int n = nums.length;
        long expected = (long) n * (n + 1) / 2;
        long actual = 0;
        for (int x : nums) actual += x;
        return (int) (expected - actual);
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }
}
