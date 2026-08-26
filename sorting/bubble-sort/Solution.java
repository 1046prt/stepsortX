// Stepsort · Bubble Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bubble-sort

public class Main {
    // Repeatedly swap adjacent out-of-order pairs; stop early if a pass is clean.
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 1, 4, 2, 8};
        bubbleSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
