// Stepsort · Bloom Filter
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-bloom-filter

public class Main {
    static java.util.Random rand = new java.util.Random(42);  // unused here
    static final boolean[] bits = new boolean[64];

    static int h1(String item) {
        long h = 0;
        for (char c : item.toCharArray()) h = (h * 31 + c) % bits.length;
        return (int) h;
    }

    static int h2(String item) {
        long h = 5381;
        for (char c : item.toCharArray()) h = (h * 33 + c) % bits.length;
        return (int) h;
    }

    static void add(String item) {
        bits[h1(item)] = true;
        bits[h2(item)] = true;
    }

    static boolean possiblyContains(String item) {
        return bits[h1(item)] && bits[h2(item)];
    }

    public static void main(String[] args) {
        for (String word : new String[]{"apple", "banana", "cherry"}) add(word);
        System.out.println("apple inserted -> present? " + possiblyContains("apple"));
        for (String word : new String[]{"date", "fig", "grape", "kiwi"}) {
            String verdict = possiblyContains(word)
                    ? "false positive (never inserted)"
                    : "definitely absent";
            System.out.println(word + " -> " + verdict);
        }
    }
}
