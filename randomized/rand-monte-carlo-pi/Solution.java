// Stepsort · Monte Carlo (π)
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-monte-carlo-pi

public class Main {
    static java.util.Random rand = new java.util.Random(42);

    // Throw darts at the unit square; count hits inside the quarter circle.
    static double estimatePi(long samples) {
        long inside = 0;
        for (long i = 0; i < samples; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            if (x * x + y * y <= 1.0) inside++;
        }
        return 4.0 * inside / samples;
    }

    public static void main(String[] args) {
        long[] sizes = {1000L, 100000L, 1000000L};
        for (long n : sizes) {
            double estimate = estimatePi(n);
            double error = Math.abs(estimate - Math.PI);
            System.out.println("samples: " + n + " estimate: "
                    + String.format("%.6f", estimate) + " error: "
                    + String.format("%.6f", error));
        }
    }
}
