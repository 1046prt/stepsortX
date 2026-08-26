// sortsort · Rod Cutting
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rod-cutting

import java.util.ArrayList;
import java.util.List;

public class Main {
    // prices[k] is the price of a piece of length k+1; returns best
    // revenue and stores the optimal cut sizes into pieces
    static int rodCutting(int[] prices, int n, List<Integer> pieces) {
        int[] dp = new int[n + 1];         // dp[L]: best revenue for length L
        int[] firstPiece = new int[n + 1]; // best size for the first cut at L
        for (int length = 1; length <= n; length++) {
            for (int first = 1; first <= length; first++) {
                int candidate = prices[first - 1] + dp[length - first];
                if (candidate > dp[length]) {
                    dp[length] = candidate;
                    firstPiece[length] = first;
                }
            }
        }
        int len = n;
        while (len > 0) {
            pieces.add(firstPiece[len]);
            len -= firstPiece[len];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        List<Integer> pieces = new ArrayList<>();
        int revenue = rodCutting(prices, 8, pieces);
        System.out.println("Best revenue: " + revenue);
        System.out.println("Piece lengths: " + pieces);
    }
}
