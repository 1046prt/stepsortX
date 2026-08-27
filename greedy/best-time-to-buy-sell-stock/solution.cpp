// Stepsort · Best Time to Buy/Sell Stock
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/best-time-to-buy-sell-stock

#include <bits/stdc++.h>
using namespace std;

int maxProfit(vector<int>& prices) {
    int minPrice = INT_MAX, maxProfit = 0;
    for (int price : prices) {
        minPrice = min(minPrice, price);
        maxProfit = max(maxProfit, price - minPrice);
    }
    return maxProfit;
}

int main() {
    vector<int> prices = {7, 1, 5, 3, 6, 4};
    cout << "max profit: " << maxProfit(prices) << endl;
    return 0;
}
