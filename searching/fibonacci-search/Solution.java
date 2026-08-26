// Stepsort · Fibonacci Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-search

public class Main {
    // Probe split points given by Fibonacci numbers on a sorted array.
    static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) return -1;
        int fibM2 = 0, fibM1 = 1;  // F(k-2), F(k-1)
        int fibM = fibM2 + fibM1;  // F(k)
        while (fibM < n) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM2 + fibM1;
        }
        int offset = -1;
        while (fibM > 1) {
            int i = Math.min(offset + fibM2, n - 1);
            if (arr[i] == target) return i;
            else if (arr[i] < target) {
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
            } else {
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
            }
        }
        if (offset + 1 < n && arr[offset + 1] == target) return offset + 1;
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
        System.out.println("index of 85: " + fibonacciSearch(data, 85));
        System.out.println("index of 42: " + fibonacciSearch(data, 42));
    }
}
