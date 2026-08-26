// Stepsort · Gnome Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gnome-sort

#include <bits/stdc++.h>
using namespace std;

// Move forward when ordered; otherwise swap back and step backward.
void gnomeSort(vector<int>& arr) {
    int i = 0;
    int n = (int)arr.size();
    while (i < n) {
        if (i == 0 || arr[i] >= arr[i - 1]) {
            i++;
        } else {
            swap(arr[i], arr[i - 1]);
            i--;
        }
    }
}

int main() {
    vector<int> data = {34, 2, 10, 9, 7, 8};
    gnomeSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
