// Stepsort · Reservoir Sampling
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-reservoir-sampling

public class Main {
    static java.util.Random rand = new java.util.Random(42);

    // Sample k items uniformly from a stream of unknown length.
    static int[] reservoirSample(int streamLength, int k) {
        int[] reservoir = new int[k];
        for (int index = 0; index < streamLength; index++) {
            int item = index + 1;  // stand-in for the arriving stream element
            if (index < k) {
                reservoir[index] = item;
            } else {
                int slot = rand.nextInt(index + 1);
                if (slot < k) reservoir[slot] = item;
            }
        }
        return reservoir;
    }

    public static void main(String[] args) {
        int[] sample = reservoirSample(20, 3);
        java.util.Arrays.sort(sample);
        StringBuilder out = new StringBuilder("sample of 3 from stream of 20:");
        for (int value : sample) out.append(" ").append(value);
        System.out.println(out.toString());
    }
}
