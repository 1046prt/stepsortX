// sortsort · Bogo Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bogo-sort

import java.util.Arrays;

public class Main {
    // Fixed-seed linear congruential generator so demos always terminate.
    static class DetRng {
        private long state;

        DetRng(long seed) {
            this.state = seed;
        }

        int below(int bound) {
            state = (state * 1103515245L + 12345L) % 2147483648L;
            return (int) ((state >> 8) % bound);
        }
    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    static void fisherYates(int[] arr, DetRng rng) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rng.below(i + 1);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    static int bogoSort(int[] arr) {
        DetRng rng = new DetRng(20240817L);
        int attempts = 0;
        while (!isSorted(arr)) {
            fisherYates(arr, rng);
            attempts++;
        }
        return attempts;
    }

    public static void main(String[] args) {
        int[] data = {4, 1, 3, 2};
        System.out.println("attempts: " + bogoSort(data));
        System.out.println("sorted: " + Arrays.toString(data));
    }
}
