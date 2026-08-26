// Stepsort · Cocktail Shaker Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cocktail-sort

public class Main {
    // Bubble passes alternate direction, tightening both ends.
    static void cocktailSort(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
            swapped = false;
            end--;
            for (int i = end; i > start; i--) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    swapped = true;
                }
            }
            start++;
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 1, 4, 2, 8, 0, 6};
        cocktailSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
