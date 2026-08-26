// Stepsort · Longest Palindromic Substring
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/longest-palindrome

#include <bits/stdc++.h>
using namespace std;

// Grow the window while it stays a palindrome and stays in bounds.
// Returns the start index and length of the widest palindrome found.
pair<int, int> expandAroundCenter(const string& s, int left, int right) {
    while (left >= 0 && right < (int)s.size() && s[left] == s[right]) {
        left--;
        right++;
    }
    return {left + 1, right - left - 1};
}

// Every palindrome has a center: a character (odd length) or a gap
// between two characters (even length). Try all 2n - 1 centers.
string longestPalindrome(const string& s) {
    int bestStart = 0, bestLen = 0;
    for (int center = 0; center < (int)s.size(); center++) {
        pair<int, int> odd = expandAroundCenter(s, center, center);
        pair<int, int> even = expandAroundCenter(s, center, center + 1);
        if (odd.second > bestLen) {
            bestStart = odd.first;
            bestLen = odd.second;
        }
        if (even.second > bestLen) {
            bestStart = even.first;
            bestLen = even.second;
        }
    }
    return s.substr(bestStart, bestLen);
}

int main() {
    vector<string> tests = {"babad", "cbbd", "forgeeksskeegfor"};
    for (const string& text : tests) {
        cout << text << " -> " << longestPalindrome(text) << endl;
    }
    return 0;
}
