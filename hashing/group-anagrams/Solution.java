// Stepsort · Group Anagrams
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/group-anagrams

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    static List<List<String>> groupAnagrams(String[] words) {
        HashMap<String, List<String>> groups = new HashMap<>();
        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String signature = new String(letters);
            groups.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        int i = 0;
        for (List<String> group : groupAnagrams(words)) {
            System.out.println("group " + (i++) + " -> " + group);
        }
    }
}
