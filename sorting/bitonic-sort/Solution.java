// Stepsort · Bitonic Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bitonic-sort

import java.util.Arrays;

public class Main {
    static void compareAndSwap(int[] arr, int i, int j, boolean dir) {
        if ((dir && arr[i] > arr[j]) || (!dir && arr[i] < arr[j])) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    static void bitonicMerge(int[] arr, int low, int count, boolean dir) {
        if (count <= 1) return;
        int mid = count / 2;
        for (int i = low; i < low + mid; i++) {
            compareAndSwap(arr, i, i + mid, dir);
        }
        bitonicMerge(arr, low, mid, dir);
        bitonicMerge(arr, low + mid, mid, dir);
    }

    // Sorting network for power-of-two lengths.
    static void bitonicSort(int[] arr, int low, int count, boolean dir) {
        if (count <= 1) return;
        int mid = count / 2;
        bitonicSort(arr, low, mid, true);
        bitonicSort(arr, low + mid, mid, false);
        bitonicMerge(arr, low, count, dir);
    }

    public static void main(String[] args) {
        int[] data = {3, 7, 4, 8, 6, 2, 1, 5};
        bitonicSort(data, 0, data.length, true);
        System.out.println("sorted: " + Arrays.toString(data));
    }
}
