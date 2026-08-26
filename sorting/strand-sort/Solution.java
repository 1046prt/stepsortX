// Stepsort · Strand Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/strand-sort

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Integer> mergeLists(List<Integer> head, List<Integer> tail) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < head.size() && j < tail.size()) {
            if (head.get(i) <= tail.get(j)) merged.add(head.get(i++));
            else merged.add(tail.get(j++));
        }
        while (i < head.size()) merged.add(head.get(i++));
        while (j < tail.size()) merged.add(tail.get(j++));
        return merged;
    }

    // Pull each increasing subsequence out, merge it into the result.
    static List<Integer> strandSort(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        while (!input.isEmpty()) {
            List<Integer> strand = new ArrayList<>();
            strand.add(input.remove(0));
            int i = 0;
            while (i < input.size()) {
                if (input.get(i) >= strand.get(strand.size() - 1)) {
                    strand.add(input.remove(i));
                } else {
                    i++;
                }
            }
            result = mergeLists(strand, result);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> data =
                new ArrayList<>(Arrays.asList(10, 2, 8, 4, 6, 1, 9, 3));
        System.out.println("sorted: " + strandSort(data));
    }
}
