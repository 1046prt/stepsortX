// sortsort · Merge Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-sort

public class Main {
    // Combine the two sorted halves arr[left..mid] and arr[mid+1..right].
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

    static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void main(String[] args) {
        int[] data = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(data, 0, data.length - 1);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
