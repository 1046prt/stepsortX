// Stepsort · Combination Sum
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/combination-sum

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] candidates = {2, 3, 6, 7};
    static int target = 7;

    static void combine(int start, int remaining, List<Integer> current, List<List<Integer>> results) {
        if (remaining == 0) {
            results.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) continue;
            current.add(candidates[i]);
            // reuse allowed: pass i, not i + 1
            combine(i, remaining - candidates[i], current, results);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> results = new ArrayList<>();
        combine(0, target, new ArrayList<>(), results);
        for (List<Integer> combo : results) {
            System.out.println(combo);
        }
    }
}
