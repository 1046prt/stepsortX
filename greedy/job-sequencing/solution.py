# Stepsort · Job Sequencing
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/job-sequencing

def job_sequencing(jobs):
    # jobs: list of (job_id, deadline, profit)
    ordered = sorted(jobs, key=lambda job: job[2], reverse=True)
    max_deadline = max(job[1] for job in jobs)
    slots = [None] * (max_deadline + 1)
    total_profit = 0

    # place each job in the latest free slot before its deadline
    for job_id, deadline, profit in ordered:
        for slot in range(min(deadline, max_deadline), 0, -1):
            if slots[slot] is None:
                slots[slot] = job_id
                total_profit += profit
                break

    scheduled = [job for job in slots if job]
    return scheduled, total_profit


if __name__ == "__main__":
    jobs = [("J1", 4, 70), ("J2", 2, 60), ("J3", 4, 50), ("J4", 3, 40), ("J5", 1, 30)]
    scheduled, profit = job_sequencing(jobs)
    print("Scheduled jobs:", scheduled)
    print("Total profit:", profit)
