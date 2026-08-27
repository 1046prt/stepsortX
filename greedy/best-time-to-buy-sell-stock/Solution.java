// Stepsort · Best Time to Buy/Sell Stock
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/best-time-to-buy-sell-stock

public class BestTimeToBuySellStock {
    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
    }
}
