// sortsort · Fractional Knapsack
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fractional-knapsack

#include <bits/stdc++.h>
using namespace std;

struct Item {
    double value;
    double weight;
};

double fractionalKnapsack(double capacity, vector<Item> items) {
    // sort by value/weight ratio descending
    sort(items.begin(), items.end(),
         [](const Item& a, const Item& b) { return a.value / a.weight > b.value / b.weight; });
    double totalValue = 0.0;
    double remaining = capacity;
    for (const Item& item : items) {
        if (remaining <= 0) break;
        double take = min(item.weight, remaining);
        totalValue += item.value * take / item.weight;
        remaining -= take;
    }
    return totalValue;
}

int main() {
    vector<Item> items = {{60, 10}, {100, 20}, {120, 30}};
    cout << fixed << setprecision(2);
    cout << "Maximum value: " << fractionalKnapsack(50, items) << endl;
    return 0;
}
