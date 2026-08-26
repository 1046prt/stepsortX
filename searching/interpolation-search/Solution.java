// sortsort · Interpolation Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interpolation-search

public class Main {
    // Probe position estimated from value distribution (sorted data).
    static int interpolationSearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi && arr[lo] <= target && target <= arr[hi]) {
            if (arr[lo] == arr[hi]) {
                return arr[lo] == target ? lo : -1;
            }
            int pos = lo + (int) ((long) (target - arr[lo]) * (hi - lo)
                    / (arr[hi] - arr[lo]));
            if (arr[pos] == target) return pos;
            else if (arr[pos] < target) lo = pos + 1;
            else hi = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30, 40, 50, 60, 70, 80};
        System.out.println("index of 50: " + interpolationSearch(data, 50));
        System.out.println("index of 45: " + interpolationSearch(data, 45));
    }
}
