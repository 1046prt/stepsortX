// Stepsort · IntroSort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/intro-sort

import java.util.Arrays;

public class IntroSort {
    static final int SIZE_THRESHOLD = 16;

    static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            int key = arr[i], j = i - 1;
            while (j >= lo && arr[j] > key) { arr[j+1] = arr[j]; j--; }
            arr[j+1] = key;
        }
    }

    static void heapSort(int[] arr, int lo, int hi) {
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>();
        for (int i = lo; i < hi; i++) pq.add(arr[i]);
        for (int i = lo; i < hi; i++) arr[i] = pq.poll();
    }

    static int medianOfThree(int[] arr, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (arr[lo] > arr[mid]) { int t = arr[lo]; arr[lo] = arr[mid]; arr[mid] = t; }
        if (arr[lo] > arr[hi-1]) { int t = arr[lo]; arr[lo] = arr[hi-1]; arr[hi-1] = t; }
        if (arr[mid] > arr[hi-1]) { int t = arr[mid]; arr[mid] = arr[hi-1]; arr[hi-1] = t; }
        int t = arr[mid]; arr[mid] = arr[hi-2]; arr[hi-2] = t;
        return arr[hi-2];
    }

    static void introSortUtil(int[] arr, int lo, int hi, int depthLimit) {
        while (hi - lo > SIZE_THRESHOLD) {
            if (depthLimit == 0) { heapSort(arr, lo, hi); return; }
            depthLimit--;
            int pivot = medianOfThree(arr, lo, hi);
            int i = lo, j = hi - 2;
            while (true) {
                while (arr[++i] < pivot);
                while (arr[--j] > pivot);
                if (i >= j) break;
                int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
            }
            int t = arr[i]; arr[i] = arr[hi-2]; arr[hi-2] = t;
            introSortUtil(arr, i+1, hi, depthLimit);
            hi = i;
        }
        insertionSort(arr, lo, hi);
    }

    public static void sort(int[] arr) {
        if (arr.length <= 1) return;
        introSortUtil(arr, 0, arr.length, 2 * (int)(Math.log(arr.length) / Math.log(2)));
    }

    public static void main(String[] args) {
        int[] data = {5, 1, 4, 2, 8};
        sort(data);
        System.out.println("sorted: " + Arrays.toString(data));
    }
}
