# sortsort · Monte Carlo (π)
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-monte-carlo-pi

import random


def estimate_pi(num_samples):
    # Throw darts at the unit square; count hits inside the quarter circle.
    inside = 0
    for _ in range(num_samples):
        x = random.random()
        y = random.random()
        if x * x + y * y <= 1.0:
            inside += 1
    return 4.0 * inside / num_samples


if __name__ == "__main__":
    random.seed(42)
    true_pi = 3.141592653589793
    for n in [1000, 100000, 1000000]:
        estimate = estimate_pi(n)
        error = abs(estimate - true_pi)
        print("samples:", n, "estimate:", round(estimate, 6), "error:", round(error, 6))
