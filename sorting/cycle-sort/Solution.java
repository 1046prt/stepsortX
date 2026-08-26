// sortsort · Cycle Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cycle-sort

import java.util.Arrays;

public class Main {
    static int cycleSort(int[] arr) {
        int n = arr.length;
        int writes = 0;
        for (int start = 0; start < n - 1; start++) {
            int item = arr[start];
            int pos = start;
            for (int i = start + 1; i < n; i++) {
                if (arr[i] < item) pos++;
            }
            if (pos == start) continue;
            while (item == arr[pos]) pos++;
            int tmp = item;
            item = arr[pos];
            arr[pos] = tmp;
            writes++;
            while (pos != start) {
                pos = start;
                for (int i = start + 1; i < n; i++) {
                    if (arr[i] < item) pos++;
                }
                while (item == arr[pos]) pos++;
                tmp = item;
                item = arr[pos];
                arr[pos] = tmp;
                writes++;
            }
        }
        return writes;
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 5, 1, 3, 4};
        System.out.println("writes: " + cycleSort(data));
        System.out.println("sorted: " + Arrays.toString(data));
    }
}
