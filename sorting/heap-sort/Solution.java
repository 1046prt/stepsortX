// Stepsort · Heap Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-sort

public class Main {
    // Push arr[root] down until the subtree rooted there is a max-heap.
    static void siftDown(int[] arr, int root, int size) {
        while (true) {
            int largest = root;
            int left = 2 * root + 1;
            int right = 2 * root + 2;
            if (left < size && arr[left] > arr[largest]) largest = left;
            if (right < size && arr[right] > arr[largest]) largest = right;
            if (largest == root) return;
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;
            root = largest;
        }
    }

    static void heapSort(int[] arr) {
        int n = arr.length;
        // Build a max-heap, then repeatedly move the max to the end.
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(arr, i, n);
        for (int end = n - 1; end > 0; end--) {
            int temp = arr[0];
            arr[0] = arr[end];
            arr[end] = temp;
            siftDown(arr, 0, end);
        }
    }

    public static void main(String[] args) {
        int[] data = {4, 10, 3, 5, 1};
        heapSort(data);
        System.out.print("sorted:");
        for (int x : data) System.out.print(" " + x);
        System.out.println();
    }
}
