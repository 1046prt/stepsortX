// Stepsort · Two Pointer Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-pointer-search

public class Main {
    // Walk both ends of a sorted array inward based on the current sum.
    static int[] twoPointerPairSum(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int total = arr[lo] + arr[hi];
            if (total == target) return new int[]{lo, hi};
            else if (total < target) lo++;
            else hi--;
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int[] hit = twoPointerPairSum(data, 24);
        int[] miss = twoPointerPairSum(data, 200);
        System.out.println("pair for 24: (" + hit[0] + ", " + hit[1] + ")");
        System.out.println("pair for 200: (" + miss[0] + ", " + miss[1] + ")");
    }
}
