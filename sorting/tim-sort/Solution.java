// sortsort · Tim Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tim-sort

public class Main {
    static final int RUN = 32;

    // Sort arr[left..right] in place.
    static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Combine adjacent sorted runs arr[left..mid] and arr[mid+1..right].
    static void merge(int[] arr, int left, int mid, int right) {
        int[] merged = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) merged[k++] = arr[i++];
            else merged[k++] = arr[j++];
        }
        while (i <= mid) merged[k++] = arr[i++];
        while (j <= right) merged[k++] = arr[j++];
        for (int t = 0; t < merged.length; t++) arr[left + t] = merged[t];
    }

    // Insertion-sort fixed-size runs, then merge runs bottom-up.
    static void timSort(int[] arr) {
        int n = arr.length;
        for (int start = 0; start < n; start += RUN) {
            insertionSort(arr, start, Math.min(start + RUN - 1, n - 1));
        }
        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                if (mid < right) merge(arr, left, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 15, 8, 90, 42, 7, 61, 27, 4, 88, 16, 55};
        timSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
