// sortsort · Mo's Algorithm
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/mo-algorithm

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> arr = {1, 1, 2, 3, 3, 4, 1, 2, 2, 1};
    vector<pair<int,int>> queries = {{0,4},{1,6},{3,9},{2,7}};
    int n = arr.size();
    int block = max(1, (int)sqrt((double)n));

    vector<int> order(queries.size());
    iota(order.begin(), order.end(), 0);
    sort(order.begin(), order.end(), [&](int a, int b) {
        int ba = queries[a].first / block, bb = queries[b].first / block;
        return ba != bb ? ba < bb : queries[a].second < queries[b].second;
    });

    unordered_map<int,int> freq;
    int distinct = 0, curL = 0, curR = -1;
    auto add = [&](int i) { if (freq[arr[i]]++ == 0) distinct++; };
    auto rem = [&](int i) { if (--freq[arr[i]] == 0) distinct--; };

    for (int qi : order) {
        auto [l, r] = queries[qi];
        while (curR < r) add(++curR);
        while (curL > l) add(--curL);
        while (curR > r) rem(curR--);
        while (curL < l) rem(curL++);
        cout << "query[" << l << "," << r << "] -> " << distinct << endl;
    }
}
