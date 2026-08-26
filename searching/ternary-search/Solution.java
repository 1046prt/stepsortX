// Stepsort · Ternary Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ternary-search

public class Main {
    // Split the sorted range into three parts using two midpoints.
    static int ternarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int third = (hi - lo) / 3;
            int m1 = lo + third;
            int m2 = hi - third;
            if (arr[m1] == target) return m1;
            if (arr[m2] == target) return m2;
            if (target < arr[m1]) {
                hi = m1 - 1;
            } else if (target > arr[m2]) {
                lo = m2 + 1;
            } else {
                lo = m1 + 1;
                hi = m2 - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {1, 4, 7, 12, 15, 19, 24, 31, 40};
        System.out.println("index of 19: " + ternarySearch(data, 19));
        System.out.println("index of 20: " + ternarySearch(data, 20));
    }
}
