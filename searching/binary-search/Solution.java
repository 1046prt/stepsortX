// Stepsort · Binary Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search

public class Main {
    // Iterative search on a sorted array.
    static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {1, 3, 5, 7, 9, 11, 13};
        System.out.println("index of 7: " + binarySearch(data, 7));
        System.out.println("index of 4: " + binarySearch(data, 4));
    }
}
