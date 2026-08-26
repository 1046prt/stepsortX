// sortsort · Quick Select
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/quick-select

public class Main {

    // deterministic pivot: value-sorted middle among first, middle, last
    static int medianOfThreeIndex(int[] arr, int lo, int hi) {
        int mid = (lo + hi) / 2;
        int x = arr[lo], y = arr[mid], z = arr[hi];
        if ((y <= x && x <= z) || (z <= x && x <= y)) return lo;
        if ((x <= y && y <= z) || (z <= y && y <= x)) return mid;
        return hi;
    }

    static int partition(int[] arr, int lo, int hi) {
        int p = medianOfThreeIndex(arr, lo, hi);
        int tmp = arr[p];
        arr[p] = arr[hi];
        arr[hi] = tmp;
        int pivot = arr[hi], store = lo;
        for (int i = lo; i < hi; i++) {
            if (arr[i] < pivot) {
                tmp = arr[i];
                arr[i] = arr[store];
                arr[store] = tmp;
                store++;
            }
        }
        tmp = arr[store];
        arr[store] = arr[hi];
        arr[hi] = tmp;
        return store;
    }

    // k-th smallest (zero-indexed); runs on a clone so data stays intact
    static int quickSelect(int[] original, int k) {
        int[] arr = original.clone();
        int lo = 0, hi = arr.length - 1;
        while (true) {
            if (lo == hi) return arr[lo];
            int p = partition(arr, lo, hi);
            if (k == p) return arr[p];
            if (k < p) hi = p - 1;
            else lo = p + 1;
        }
    }

    public static void main(String[] args) {
        int[] data = {7, 2, 9, 4, 1, 8, 6, 3, 5};
        System.out.println("data: " + java.util.Arrays.toString(data));
        int[] ks = {0, 3, 8};
        for (int k : ks)
            System.out.println("rank " + (k + 1) + " smallest: " + quickSelect(data, k));
    }
}
