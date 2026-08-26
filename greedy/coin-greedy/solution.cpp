// sortsort · Coin Change (Greedy)
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/coin-greedy

#include <bits/stdc++.h>
using namespace std;

vector<int> coinChangeGreedy(int amount, vector<int> denominations) {
    // repeatedly take the largest coin that fits
    sort(denominations.begin(), denominations.end(), greater<int>());
    vector<int> change;
    for (int coin : denominations) {
        while (amount >= coin) {
            amount -= coin;
            change.push_back(coin);
        }
    }
    return change;
}

int main() {
    vector<int> change = coinChangeGreedy(93, {25, 10, 5, 1});
    cout << "Change for 93:";
    for (int coin : change) cout << " " << coin;
    cout << endl;
    cout << "Coins used: " << change.size() << endl;
    vector<int> big = coinChangeGreedy(2890, {1000, 500, 100, 50, 20, 10, 5, 1});
    cout << "Change for 2890 uses " << big.size() << " coins" << endl;
    return 0;
}
