// Stepsort · Longest Increasing Subsequence
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lis

public class Main {
    static int lowerBound(int[] a, int len, int key) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] < key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    static void lis(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];     // tails[k] = smallest tail of length k+1 subsequence
        int[] tailIdx = new int[n];
        int[] prev = new int[n];      // predecessor index for reconstruction
        int len = 0;
        for (int i = 0; i < n; i++) {
            int pos = lowerBound(tails, len, nums[i]);
            tails[pos] = nums[i];
            tailIdx[pos] = i;
            if (pos == len) len++;
            prev[i] = pos > 0 ? tailIdx[pos - 1] : -1;
        }
        System.out.println("LIS length: " + len);
        StringBuilder sb = new StringBuilder();
        for (int k = tailIdx[len - 1]; k >= 0; k = prev[k]) sb.insert(0, nums[k] + " ");
        System.out.println("One LIS: " + sb.toString().trim());
    }

    public static void main(String[] args) {
        lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }
}
