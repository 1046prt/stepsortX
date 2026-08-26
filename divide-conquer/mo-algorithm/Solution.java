// sortsort · Mo's Algorithm
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/mo-algorithm

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 1, 2, 2, 1};
        int[][] queries = {{0,4},{1,6},{3,9},{2,7}};
        int n = arr.length, block = Math.max(1, (int) Math.sqrt(n));

        Integer[] order = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) order[i] = i;
        final int[] curL = {0}, curR = {-1}, distinct = {0};
        Map<Integer, Integer> freq = new HashMap<>();
        Runnable nothingYet = () -> {};
        java.util.function.Consumer<Integer>[] ops = null;   // placeholder-free approach below

        Arrays.sort(order, (a, b) -> {
            int ba = queries[a][0] / block, bb = queries[b][0] / block;
            return ba != bb ? ba - bb : queries[a][1] - queries[b][1];
        });

        for (int qi : order) {
            int L = queries[qi][0], R = queries[qi][1];
            while (curR[0] < R) { int v = arr[++curR[0]]; distinct[0] += (freq.merge(v, 1, Integer::sum) == 1) ? 1 : 0; }
            while (curL[0] > L) { int v = arr[--curL[0]]; distinct[0] += (freq.merge(v, 1, Integer::sum) == 1) ? 1 : 0; }
            while (curR[0] > R) { int v = arr[curR[0]--]; distinct[0] -= (freq.merge(v, -1, Integer::sum) == 0) ? 1 : 0; }
            while (curL[0] < L) { int v = arr[curL[0]++]; distinct[0] -= (freq.merge(v, -1, Integer::sum) == 0) ? 1 : 0; }
            System.out.println("query[" + L + "," + R + "] -> " + distinct[0]);
        }
    }
}
