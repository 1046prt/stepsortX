// Stepsort · Linear Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/linear-search

public class Main {
    // Scan every element from left to right.
    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 7, 1, 9, 5};
        System.out.println("index of 7: " + linearSearch(data, 7));
        System.out.println("index of 3: " + linearSearch(data, 3));
    }
}
