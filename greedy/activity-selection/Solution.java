// sortsort · Activity Selection
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/activity-selection

import java.util.*;

public class Main {

    static class Activity {
        String name;
        int start, finish;

        Activity(String name, int start, int finish) {
            this.name = name;
            this.start = start;
            this.finish = finish;
        }
    }

    static List<String> activitySelection(Activity[] activities) {
        // greedy: always take the activity that finishes first
        Arrays.sort(activities, (a, b) -> Integer.compare(a.finish, b.finish));
        List<String> selected = new ArrayList<>();
        int lastFinish = 0;
        for (Activity act : activities) {
            if (selected.isEmpty() || act.start >= lastFinish) {
                selected.add(act.name);
                lastFinish = act.finish;
            }
        }
        return selected;
    }

    public static void main(String[] args) {
        Activity[] activities = {
            new Activity("A1", 1, 4), new Activity("A2", 3, 5), new Activity("A3", 0, 6),
            new Activity("A4", 5, 7), new Activity("A5", 3, 9), new Activity("A6", 5, 8),
        };
        List<String> chosen = activitySelection(activities);
        System.out.println("Selected activities: " + chosen);
        System.out.println("Maximum count: " + chosen.size());
    }
}
