// sortsort · Pancake Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/pancake-sort

import java.util.Arrays;

public class Main {
    // Reverse the prefix arr[0..k] in place.
    static void flip(int[] arr, int k) {
        int left = 0, right = k;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }

    static int findMaxIndex(int[] arr, int limit) {
        int best = 0;
        for (int i = 1; i < limit; i++) {
            if (arr[i] > arr[best]) best = i;
        }
        return best;
    }

    static void pancakeSort(int[] arr) {
        // Max of the unsorted prefix to the front, then flipped into place.
        for (int size = arr.length; size > 1; size--) {
            int maxIdx = findMaxIndex(arr, size);
            if (maxIdx == size - 1) continue;
            if (maxIdx != 0) flip(arr, maxIdx);
            flip(arr, size - 1);
        }
    }

    public static void main(String[] args) {
        int[] data = {6, 2, 9, 1, 5, 8};
        pancakeSort(data);
        System.out.println("sorted: " + Arrays.toString(data));
    }
}
