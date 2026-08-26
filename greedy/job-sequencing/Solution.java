// Stepsort · Job Sequencing
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/job-sequencing

import java.util.*;

public class Main {

    static class Job {
        String id;
        int deadline, profit;

        Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    static int jobSequencing(List<Job> jobs, List<String> scheduledOut) {
        jobs.sort((a, b) -> Integer.compare(b.profit, a.profit));
        int maxDeadline = 0;
        for (Job job : jobs) maxDeadline = Math.max(maxDeadline, job.deadline);

        String[] slots = new String[maxDeadline + 1];
        int totalProfit = 0;

        // place each job in the latest free slot before its deadline
        for (Job job : jobs) {
            for (int slot = Math.min(job.deadline, maxDeadline); slot >= 1; slot--) {
                if (slots[slot] == null) {
                    slots[slot] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        for (String id : slots) {
            if (id != null) scheduledOut.add(id);
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>(List.of(
            new Job("J1", 4, 70), new Job("J2", 2, 60), new Job("J3", 4, 50),
            new Job("J4", 3, 40), new Job("J5", 1, 30)));
        List<String> scheduled = new ArrayList<>();
        int profit = jobSequencing(jobs, scheduled);
        System.out.println("Scheduled jobs: " + scheduled);
        System.out.println("Total profit: " + profit);
    }
}
