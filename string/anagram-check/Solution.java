// sortsort · Anagram Check
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/anagram-check

public class Main {
    // Frequency count over the 26 lowercase letters.
    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] counts = new int[26];
        for (char ch : a.toCharArray()) counts[ch - 'a']++;
        for (char ch : b.toCharArray()) {
            counts[ch - 'a']--;
            if (counts[ch - 'a'] < 0) return false; // b needs a letter a lacks
        }
        return true; // equal lengths plus no deficit implies no surplus
    }

    public static void main(String[] args) {
        String[][] pairs = {
            {"listen", "silent"},
            {"triangle", "integral"},
            {"hello", "world"},
            {"aab", "abb"},
        };
        for (String[] pair : pairs) {
            System.out.println(pair[0] + " vs " + pair[1]
                    + " -> " + isAnagram(pair[0], pair[1]));
        }
    }
}
