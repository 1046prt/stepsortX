// sortsort · Daily Temperatures
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/daily-temperatures

#include <bits/stdc++.h>
using namespace std;

vector<int> dailyTemperatures(vector<int>& temps) {
    int n = temps.size();
    vector<int> answer(n, 0);
    stack<int> st;  // indices still waiting for a warmer day
    for (int i = 0; i < n; i++) {
        while (!st.empty() && temps[st.top()] < temps[i]) {
            int j = st.top();
            st.pop();
            answer[j] = i - j;
        }
        st.push(i);
    }
    return answer;
}

void printVector(const vector<int>& v) {
    for (size_t i = 0; i < v.size(); i++) {
        cout << v[i];
        if (i + 1 < v.size()) cout << " ";
    }
    cout << endl;
}

int main() {
    vector<int> a = {73, 74, 75, 71, 69, 72, 76, 73};
    vector<int> b = {30, 40, 50, 60};
    vector<int> c = {5, 4, 3, 2};
    printVector(dailyTemperatures(a));
    printVector(dailyTemperatures(b));
    printVector(dailyTemperatures(c));
    return 0;
}
