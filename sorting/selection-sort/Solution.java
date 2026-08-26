// sortsort · Selection Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/selection-sort

public class Main {
    // Pick the minimum of the unsorted part and move it to the front.
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};
        selectionSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
