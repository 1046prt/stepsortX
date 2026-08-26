// sortsort · Jump Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/jump-search

public class Main {
    // Jump ahead in blocks of size sqrt(n), then scan the block.
    static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) return -1;
        int step = Math.max(1, (int) Math.sqrt(n));
        int prev = 0;
        int curr = Math.min(step, n);
        while (arr[curr - 1] < target) {
            prev = curr;
            if (curr == n) return -1;
            curr = Math.min(curr + step, n);
        }
        for (int i = prev; i < curr; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {1, 3, 5, 7, 9, 12, 15, 18, 21};
        System.out.println("index of 12: " + jumpSearch(data, 12));
        System.out.println("index of 10: " + jumpSearch(data, 10));
    }
}
