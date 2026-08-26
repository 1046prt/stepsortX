// sortsort · Longest Substring Without Repeating
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/longest-substring-without-repeating

public class Main {
    static String longestUnique(String s) {
        int[] last = new int[256];
        java.util.Arrays.fill(last, -1);
        int start = 0, bestLen = 0, bestStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last[c] >= start) start = last[c] + 1;
            last[c] = i;
            if (i - start + 1 > bestLen) {
                bestLen = i - start + 1;
                bestStart = start;
            }
        }
        return bestLen + " " + s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        System.out.println(longestUnique("abcabcbb"));
    }
}
