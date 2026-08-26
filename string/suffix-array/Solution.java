// sortsort · Suffix Array
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-array

import java.util.Arrays;

public class Main {
    // Negative when suffix i is lexicographically smaller than suffix j.
    static int compareSuffixes(String text, int i, int j) {
        int n = text.length();
        while (i < n && j < n && text.charAt(i) == text.charAt(j)) {
            i++; j++;
        }
        if (i >= n) return -1;
        if (j >= n) return 1;
        return Character.compare(text.charAt(i), text.charAt(j));
    }

    public static void main(String[] args) {
        String text = "banana";
        Integer[] sa = new Integer[text.length()];
        for (int i = 0; i < sa.length; i++) sa[i] = i;

        Arrays.sort(sa, (a, b) -> compareSuffixes(text, a, b));

        System.out.println("text: " + text);
        System.out.println("suffix array: " + Arrays.toString(sa));
        for (int idx : sa) System.out.println(idx + ": " + text.substring(idx));
    }
}
