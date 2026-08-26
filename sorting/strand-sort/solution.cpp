// sortsort · Strand Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/strand-sort

#include <bits/stdc++.h>
using namespace std;

vector<int> mergeLists(const vector<int>& head, const vector<int>& tail) {
    vector<int> merged;
    size_t i = 0, j = 0;
    while (i < head.size() && j < tail.size()) {
        if (head[i] <= tail[j]) merged.push_back(head[i++]);
        else merged.push_back(tail[j++]);
    }
    while (i < head.size()) merged.push_back(head[i++]);
    while (j < tail.size()) merged.push_back(tail[j++]);
    return merged;
}

vector<int> strandSort(vector<int> input) {
    // Pull each increasing subsequence out, merge it into the result.
    vector<int> result;
    while (!input.empty()) {
        vector<int> strand;
        strand.push_back(input.front());
        input.erase(input.begin());
        for (size_t i = 0; i < input.size(); ) {
            if (input[i] >= strand.back()) {
                strand.push_back(input[i]);
                input.erase(input.begin() + i);
            } else {
                i++;
            }
        }
        result = mergeLists(strand, result);
    }
    return result;
}

int main() {
    vector<int> data = {10, 2, 8, 4, 6, 1, 9, 3};
    cout << "sorted:";
    for (int x : strandSort(data)) cout << " " << x;
    cout << endl;
    return 0;
}
