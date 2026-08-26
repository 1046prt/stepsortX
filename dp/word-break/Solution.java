// Stepsort · Word Break
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/word-break

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    // Returns null when s cannot be segmented, else one valid word sequence
    static List<String> wordBreak(String s, Set<String> words) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];  // dp[i]: prefix of length i is breakable
        int[] parent = new int[n + 1];      // start index of the word ending at i
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    parent[i] = j;
                    break;
                }
            }
        }
        if (!dp[n]) return null;
        List<String> parts = new ArrayList<>();
        int i = n;
        while (i > 0) {
            parts.add(s.substring(parent[i], i));
            i = parent[i];
        }
        Collections.reverse(parts);
        return parts;
    }

    static String describe(List<String> parts) {
        return parts == null ? "not segmentable" : String.join(" ", parts);
    }

    public static void main(String[] args) {
        Set<String> words = new HashSet<>(Set.of("cat", "cats", "and", "sand", "dog"));
        System.out.println("catsanddog -> " + describe(wordBreak("catsanddog", words)));
        System.out.println("catsandog -> " + describe(wordBreak("catsandog", words)));
    }
}
