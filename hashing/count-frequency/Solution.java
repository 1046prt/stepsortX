// sortsort · Frequency Count
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-frequency

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    static HashMap<Integer, Integer> countFrequency(int[] data) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int x : data) {
            counts.put(x, counts.getOrDefault(x, 0) + 1);
        }
        return counts;
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 7, 4, 8, 2, 4, 9, 7, 4};
        HashMap<Integer, Integer> counts = countFrequency(data);
        ArrayList<Integer> keys = new ArrayList<>(counts.keySet());
        Collections.sort(keys);
        int bestKey = data[0];
        for (int key : keys) {
            System.out.println(key + " occurs " + counts.get(key) + " time(s)");
            if (counts.get(key) > counts.get(bestKey)) bestKey = key;
        }
        System.out.println("most frequent: " + bestKey
                + " (" + counts.get(bestKey) + " times)");
    }
}
