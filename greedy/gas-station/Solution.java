// sortsort · Gas Station
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gas-station

public class Main {

    static int gasStationStart(int[] gas, int[] cost) {
        // feasible only if the circuit has enough gas overall
        long totalGas = 0, totalCost = 0;
        for (int g : gas) totalGas += g;
        for (int c : cost) totalCost += c;
        if (totalGas < totalCost) return -1;

        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            // any station reached with negative tank cannot be the start
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("Start station index: " + gasStationStart(gas, cost));
    }
}
