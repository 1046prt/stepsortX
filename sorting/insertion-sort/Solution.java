// sortsort · Insertion Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/insertion-sort

public class Main {
    // Insert each element into its place among the already-sorted prefix.
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] data = {12, 31, 25, 8, 32, 17};
        insertionSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
