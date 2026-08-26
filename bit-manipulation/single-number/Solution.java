// Stepsort · Single Number (XOR)
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/single-number

public class Main {
    // XOR of pairs cancels out, leaving the unique element
    static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) result ^= num;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{7}));
    }
}
