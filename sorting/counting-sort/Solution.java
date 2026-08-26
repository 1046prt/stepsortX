// sortsort · Counting Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/counting-sort

public class Main {
    // Tally occurrences of each value, assuming non-negative integers.
    static int[] countingSort(int[] arr) {
        if (arr.length == 0) return arr;
        int maxValue = arr[0];
        for (int value : arr) maxValue = Math.max(maxValue, value);
        int[] counts = new int[maxValue + 1];
        for (int value : arr) counts[value]++;
        int[] result = new int[arr.length];
        int idx = 0;
        for (int value = 0; value <= maxValue; value++) {
            for (int c = 0; c < counts[value]; c++) result[idx++] = value;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 2, 8, 3, 3, 1};
        int[] sorted = countingSort(data);
        System.out.print("sorted:");
        for (int x : sorted) System.out.print(" " + x);
        System.out.println();
    }
}
