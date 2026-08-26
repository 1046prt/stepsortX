# sortsort · Gas Station
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gas-station

def gas_station_start(gas, cost):
    # feasible only if the circuit has enough gas overall
    if sum(gas) < sum(cost):
        return -1
    tank = 0
    start = 0
    for i in range(len(gas)):
        tank += gas[i] - cost[i]
        # any station reached with negative tank cannot be the start
        if tank < 0:
            start = i + 1
            tank = 0
    return start


if __name__ == "__main__":
    gas = [1, 2, 3, 4, 5]
    cost = [3, 4, 5, 1, 2]
    print("Start station index:", gas_station_start(gas, cost))
