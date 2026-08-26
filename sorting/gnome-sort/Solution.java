// Stepsort · Gnome Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gnome-sort

public class Main {
    // Move forward when ordered; otherwise swap back and step backward.
    static void gnomeSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (i == 0 || arr[i] >= arr[i - 1]) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                i--;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {34, 2, 10, 9, 7, 8};
        gnomeSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
