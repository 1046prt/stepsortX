// sortsort · Fisher-Yates Shuffle
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-shuffle

public class Main {
    static java.util.Random rand = new java.util.Random(42);

    // In-place, unbiased: each of the n! orders equally likely.
    static void fisherYatesShuffle(int[] a, java.util.Random rng) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);  // uniform in [0, i]
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("original: " + java.util.Arrays.toString(original));

        int[] firstRun = original.clone();
        fisherYatesShuffle(firstRun, new java.util.Random(1234));
        System.out.println("shuffle with seed 1234: "
                + java.util.Arrays.toString(firstRun));

        int[] secondRun = original.clone();
        fisherYatesShuffle(secondRun, new java.util.Random(9876));
        System.out.println("shuffle with seed 9876: "
                + java.util.Arrays.toString(secondRun));
    }
}
