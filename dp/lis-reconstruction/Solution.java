// sortsort · LIS Reconstruction
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lis-reconstruction

static int[] lisReconstruction(int[] arr) {
    int n = arr.length;
    List<Integer> tails = new ArrayList<>();
    int[] tailIdx = new int[n], prev = new int[n], dp = new int[n];
    Arrays.fill(prev, -1);
    for (int i = 0; i < n; i++) {
        int pos = Collections.binarySearch(tails, arr[i]);
        if (pos < 0) pos = -pos - 1;
        if (pos == tails.size()) tails.add(arr[i]); else tails.set(pos, arr[i]);
        tailIdx[pos] = i; dp[i] = pos + 1;
        prev[i] = pos > 0 ? tailIdx[pos - 1] : -1;
    }
    int length = tails.size(), k = tailIdx[length - 1];
    int[] lis = new int[length];
    for (int i = length - 1; i >= 0; i--) { lis[i] = arr[k]; k = prev[k]; }
    return lis;
}
