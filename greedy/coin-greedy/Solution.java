// sortsort · Coin Change (Greedy)
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/coin-greedy

import java.util.*;

public class Main {

    static List<Integer> coinChangeGreedy(int amount, int[] denominations) {
        // repeatedly take the largest coin that fits
        int[] coins = denominations.clone();
        Arrays.sort(coins);
        List<Integer> change = new ArrayList<>();
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                change.add(coins[i]);
            }
        }
        return change;
    }

    public static void main(String[] args) {
        System.out.println("Change for 93: " + coinChangeGreedy(93, new int[]{25, 10, 5, 1}));
        System.out.println("Coins used: " + coinChangeGreedy(93, new int[]{25, 10, 5, 1}).size());
        System.out.println("Change for 2890: "
            + coinChangeGreedy(2890, new int[]{1000, 500, 100, 50, 20, 10, 5, 1}));
    }
}
