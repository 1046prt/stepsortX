// sortsort · Subset Sum
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-sum

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] numbers = {3, 34, 4, 12, 5, 2};
    static int target = 9;
    static List<Integer> answer = null;

    static void findSubset(int i, int remaining, List<Integer> current) {
        if (remaining == 0) {
            answer = new ArrayList<>(current);
            return;
        }
        if (i >= numbers.length || remaining < 0 || answer != null) return;
        // try including numbers[i]
        current.add(numbers[i]);
        findSubset(i + 1, remaining - numbers[i], current);
        if (answer != null) return;
        current.remove(current.size() - 1);
        // try excluding numbers[i]
        findSubset(i + 1, remaining, current);
    }

    public static void main(String[] args) {
        findSubset(0, target, new ArrayList<>());
        if (answer != null) {
            System.out.println("Subset: " + answer);
        } else {
            System.out.println("No subset sums to " + target);
        }
    }
}
