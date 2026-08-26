// sortsort · Kth Largest Element
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kth-largest-element

import java.util.Random;

public class Main {
    private static final Random RAND = new Random();

    private static int partition(int[] a, int left, int right) {
        int pivotIndex = left + RAND.nextInt(right - left + 1);
        int tmp = a[pivotIndex]; a[pivotIndex] = a[right]; a[right] = tmp;
        int pivot = a[right];
        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivot) {
                tmp = a[i]; a[i] = a[store]; a[store++] = tmp;
            }
        }
        tmp = a[store]; a[store] = a[right]; a[right] = tmp;
        return store;
    }

    public static int quickSelect(int[] a, int k) {
        // Returns kth largest by partitioning toward index n - k.
        int left = 0, right = a.length - 1;
        int target = a.length - k;
        while (true) {
            int p = partition(a, left, right);
            if (p == target) return a[p];
            if (p < target) left = p + 1;
            else right = p - 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println("k=2 -> " + quickSelect(nums.clone(), 2)); // 5
        System.out.println("k=4 -> " + quickSelect(nums.clone(), 4)); // 3
    }
}
