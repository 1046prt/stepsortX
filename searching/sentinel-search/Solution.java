// Stepsort · Sentinel Linear Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sentinel-search

public class Main {
    // Park the target at the end so the scan needs no bounds check.
    static int sentinelSearch(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) return -1;
        int last = arr[n - 1];
        arr[n - 1] = target;
        int i = 0;
        while (arr[i] != target) i++;
        arr[n - 1] = last;
        if (i < n - 1 || arr[n - 1] == target) return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 7, 1, 9, 5};
        System.out.println("index of 9: " + sentinelSearch(data, 9));
        System.out.println("index of 5: " + sentinelSearch(data, 5));
        System.out.println("index of 3: " + sentinelSearch(data, 3));
    }
}
