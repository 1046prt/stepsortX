// Stepsort · Comb Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/comb-sort

public class Main {
    // Compare items gap apart, shrinking the gap by a factor of 1.3.
    static void combSort(int[] arr) {
        int n = arr.length;
        int gap = n;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            gap = Math.max(1, (int) (gap / 1.3));
            swapped = false;
            for (int i = 0; i + gap < n; i++) {
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {8, 4, 1, 56, 3, 44, 20};
        combSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
