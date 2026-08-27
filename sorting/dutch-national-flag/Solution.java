// Stepsort · Dutch National Flag
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dutch-national-flag

import java.util.Arrays;

public class DutchNationalFlag {
    static int[] partition(int[] arr) {
        int pivot = arr[arr.length / 2];
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] < pivot) {
                int t = arr[low]; arr[low] = arr[mid]; arr[mid] = t;
                low++; mid++;
            } else if (arr[mid] == pivot) {
                mid++;
            } else {
                int t = arr[mid]; arr[mid] = arr[high]; arr[high] = t;
                high--;
            }
        }
        return new int[]{low, mid};
    }

    public static void main(String[] args) {
        int[] data = {2, 0, 1, 2, 1, 0};
        int[] bounds = partition(data);
        System.out.println("partitioned: " + Arrays.toString(data));
        System.out.println("boundaries: low=" + bounds[0] + " mid=" + bounds[1]);
    }
}
