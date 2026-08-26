// Stepsort · LIS Reconstruction
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lis-reconstruction

pair<int,vector<int>> lisReconstruction(vector<int>& arr) {
    int n = arr.size();
    vector<int> tails, tailIdx(n), prev(n, -1), dp(n);
    for (int i = 0; i < n; i++) {
        int pos = lower_bound(tails.begin(), tails.end(), arr[i]) - tails.begin();
        if (pos == (int)tails.size()) tails.push_back(arr[i]);
        else tails[pos] = arr[i];
        tailIdx[pos] = i;
        dp[i] = pos + 1;
        prev[i] = pos > 0 ? tailIdx[pos - 1] : -1;
    }
    int length = tails.size();
    vector<int> lis;
    for (int k = tailIdx[length-1]; k != -1; k = prev[k]) lis.push_back(arr[k]);
    reverse(lis.begin(), lis.end());
    return {length, lis};
}
