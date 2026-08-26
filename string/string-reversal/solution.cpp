// Stepsort · String Reversal
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/string-reversal

#include <bits/stdc++.h>
using namespace std;

// Two pointers swap characters while moving toward the middle.
void reverseString(string& s) {
    int left = 0;
    int right = (int)s.size() - 1;
    while (left < right) {
        swap(s[left], s[right]);
        left++;
        right--;
    }
}

int main() {
    vector<string> tests = {"hello", "algorithm", "racecar", ""};
    for (const string& original : tests) {
        string reversed = original;
        reverseString(reversed);
        cout << original << " -> " << reversed << endl;
    }
    return 0;
}
