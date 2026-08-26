// Stepsort · Permutations
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/permutations

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] items = {1, 2, 3};

    static void backtrack(List<Integer> current, boolean[] used) {
        if (current.size() == items.length) {
            System.out.println(current);
            return;
        }
        for (int i = 0; i < items.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(items[i]);
                backtrack(current, used);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        backtrack(new ArrayList<>(), new boolean[items.length]);
    }
}
