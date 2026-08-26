// sortsort · Las Vegas Sort
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-las-vegas-sort

public class Main {
    static java.util.Random rand = new java.util.Random(42);

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) return false;
        }
        return true;
    }

    static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    // Las Vegas scheme: random time, guaranteed correct output.
    static int lasVegasSort(int[] a) {
        int attempts = 0;
        while (!isSorted(a)) {
            shuffle(a);
            attempts++;
        }
        return attempts;
    }

    public static void main(String[] args) {
        int[] data = {5, 2, 9, 1, 7};
        int attempts = lasVegasSort(data);
        System.out.println("attempts needed: " + attempts);
        System.out.println("sorted: " + java.util.Arrays.toString(data));
    }
}
