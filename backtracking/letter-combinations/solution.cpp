// Stepsort · Letter Combinations
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/letter-combinations

#include <bits/stdc++.h>
using namespace std;

const vector<string> phone = {"",    "",    "abc", "def", "ghi",
                              "jkl", "mno", "pqrs", "tuv", "wxyz"};

void backtrack(const string& digits, int idx, string& current, vector<string>& results) {
    if (idx == (int)digits.size()) {
        results.push_back(current);
        return;
    }
    for (char ch : phone[digits[idx] - '0']) {
        current.push_back(ch);
        backtrack(digits, idx + 1, current, results);
        current.pop_back();
    }
}

int main() {
    string digits = "23";
    vector<string> results;
    string current;
    backtrack(digits, 0, current, results);
    for (const string& s : results) cout << s << " ";
    cout << endl;
    cout << "Total: " << results.size() << endl;
    return 0;
}
