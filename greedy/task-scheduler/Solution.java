// Stepsort · Task Scheduler
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/task-scheduler

public class Main {

    static int leastInterval(char[] tasks, int cooldown) {
        // idle units are bounded by the most frequent task
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        int maxFreq = 0;
        for (int f : freq) maxFreq = Math.max(maxFreq, f);

        int countMax = 0;
        for (int f : freq) {
            if (f == maxFreq) countMax++;
        }
        return Math.max(tasks.length, (maxFreq - 1) * (cooldown + 1) + countMax);
    }

    public static void main(String[] args) {
        System.out.println("Minimum units: "
            + leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println("Minimum units: "
            + leastInterval(new char[]{'A', 'C', 'A', 'B', 'D', 'B'}, 1));
    }
}
