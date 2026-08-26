// sortsort · Shell Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/shell-sort

public class Main {
    // Gapped insertion sorts with a gap that halves every pass.
    static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {12, 34, 54, 2, 3};
        shellSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
