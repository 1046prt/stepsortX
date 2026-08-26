// Stepsort · Radix Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/radix-sort

public class Main {
    // Stable counting sort keyed by the digit at place value exp.
    static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] counts = new int[10];
        for (int value : arr) counts[(value / exp) % 10]++;
        for (int digit = 1; digit < 10; digit++) counts[digit] += counts[digit - 1];
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            counts[digit]--;
            output[counts[digit]] = arr[i];
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    // LSD passes, one per decimal digit of the maximum value.
    static void radixSort(int[] arr) {
        if (arr.length == 0) return;
        int maxValue = arr[0];
        for (int value : arr) maxValue = Math.max(maxValue, value);
        for (int exp = 1; maxValue / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    public static void main(String[] args) {
        int[] data = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
