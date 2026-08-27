// Stepsort · Boyer-Moore Majority Vote
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boyer-moore-majority-vote

public class BoyerMooreMajorityVote {
    static int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) { candidate = num; count = 1; }
            else if (num == candidate) count++;
            else count--;
        }
        int occurrences = 0;
        for (int num : nums) if (num == candidate) occurrences++;
        return occurrences > nums.length / 2 ? candidate : -1;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{3, 3, 4, 2, 3, 3, 3})); // 3
    }
}
