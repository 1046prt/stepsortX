// sortsort · Pigeonhole Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/pigeonhole-sort

import java.util.Arrays;

public class Main {
    static void pigeonholeSort(int[] arr) {
        if (arr.length == 0) return;
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value < lo) lo = value;
            if (value > hi) hi = value;
        }
        int[] holes = new int[hi - lo + 1];
        for (int value : arr) holes[value - lo]++;
        int i = 0;
        for (int offset = 0; offset < holes.length; offset++) {
            while (holes[offset] > 0) {
                arr[i++] = offset + lo;
                holes[offset]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {9, 3, 7, 1, 8, 3, 5};
        pigeonholeSort(data);
        System.out.println("sorted: " + Arrays.toString(data));
    }
}
