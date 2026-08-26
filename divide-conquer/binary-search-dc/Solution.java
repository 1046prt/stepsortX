// Stepsort · Binary Search (D&C)
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search-dc

public class Main {

    // recursive divide-and-conquer search over a sorted array
    static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) return binarySearch(arr, target, mid + 1, high);
        return binarySearch(arr, target, low, mid - 1);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.print("sorted data:");
        for (int value : data) System.out.print(" " + value);
        System.out.println();
        int[] targets = {23, 2, 91, 40};
        for (int target : targets) {
            int index = binarySearch(data, target, 0, data.length - 1);
            if (index == -1)
                System.out.println(target + " not found");
            else
                System.out.println(target + " found at index " + index);
        }
    }
}
