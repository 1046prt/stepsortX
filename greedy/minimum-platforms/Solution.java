// sortsort · Minimum Platforms
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/minimum-platforms

import java.util.*;

public class Main {

    static int minimumPlatforms(int[] arrivals, int[] departures) {
        // sweep two sorted timelines with two pointers
        Arrays.sort(arrivals);
        Arrays.sort(departures);
        int platforms = 0;
        int maxNeeded = 0;
        int i = 0, j = 0;
        while (i < arrivals.length) {
            if (arrivals[i] <= departures[j]) {
                platforms++;
                maxNeeded = Math.max(maxNeeded, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }
        return maxNeeded;
    }

    public static void main(String[] args) {
        int[] arrivals = {900, 1100, 1235, 1300, 1500};
        int[] departures = {1000, 1200, 1240, 1320, 1800};
        System.out.println("Platforms needed: " + minimumPlatforms(arrivals, departures));
    }
}
