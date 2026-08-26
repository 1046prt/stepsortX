// Stepsort · Bucket Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bucket-sort

import java.util.ArrayList;
import java.util.List;

public class Main {
    // Standard insertion sort applied within one bucket.
    static void insertionSort(List<Double> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            double key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }

    // Scatter values in [0, 1) across n buckets, sort each, concatenate.
    static List<Double> bucketSort(double[] arr) {
        int n = arr.length;
        List<List<Double>> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++) buckets.add(new ArrayList<>());
        for (double value : arr) {
            buckets.get(Math.min((int) (value * n), n - 1)).add(value);
        }
        List<Double> result = new ArrayList<>();
        for (List<Double> bucket : buckets) {
            insertionSort(bucket);
            result.addAll(bucket);
        }
        return result;
    }

    public static void main(String[] args) {
        double[] data = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51};
        System.out.println("sorted: " + bucketSort(data));
    }
}
