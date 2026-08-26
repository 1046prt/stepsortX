// sortsort · KMP Pattern Matching
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kmp

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // lps[i] = length of the longest proper prefix of pattern[0..i]
    // that is also a suffix of it.
    static int[] buildLps(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int length = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else if (length > 0) {
                length = lps[length - 1];
            } else {
                i++;
            }
        }
        return lps;
    }

    // Scan the text once, falling back along the LPS table on mismatch.
    static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return matches;
        int[] lps = buildLps(pattern);
        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    matches.add(i - m);
                    j = lps[j - 1];
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        System.out.println("text: " + text);
        System.out.println("pattern: " + pattern);
        System.out.println("lps table: " + Arrays.toString(buildLps(pattern)));
        System.out.println("found at: " + kmpSearch(text, pattern));
    }
}
