// sortsort · Fractional Knapsack
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fractional-knapsack

import java.util.*;

public class Main {

    static class Item {
        double value, weight;

        Item(double value, double weight) {
            this.value = value;
            this.weight = weight;
        }

        double ratio() {
            return value / weight;
        }
    }

    static double fractionalKnapsack(double capacity, List<Item> items) {
        // sort by value/weight ratio descending
        items.sort((a, b) -> Double.compare(b.ratio(), a.ratio()));
        double totalValue = 0.0;
        double remaining = capacity;
        for (Item item : items) {
            if (remaining <= 0) break;
            double take = Math.min(item.weight, remaining);
            totalValue += item.value * take / item.weight;
            remaining -= take;
        }
        return totalValue;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>(
            List.of(new Item(60, 10), new Item(100, 20), new Item(120, 30)));
        System.out.printf("Maximum value: %.2f%n", fractionalKnapsack(50, items));
    }
}
