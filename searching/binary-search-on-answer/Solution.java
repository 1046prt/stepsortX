// Stepsort · Binary Search on Answer
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search-on-answer

public class Main {
    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int H = 8;
        int lo = 1, hi = Arrays.stream(piles).max().getAsInt(), ans = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long hours = 0;
            for (int p : piles) hours += (p + mid - 1L) / mid;
            if (hours <= H) { ans = mid; hi = mid - 1; }
            else lo = mid + 1;
        }
        System.out.println("min speed: " + ans);   // 4
    }
}
