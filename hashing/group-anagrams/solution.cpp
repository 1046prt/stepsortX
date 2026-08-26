// sortsort · Group Anagrams
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/group-anagrams

#include <bits/stdc++.h>
using namespace std;

vector<vector<string>> group_anagrams(const vector<string>& words) {
    map<string, vector<string>> groups;
    for (const string& word : words) {
        string signature = word;
        sort(signature.begin(), signature.end());
        groups[signature].push_back(word);
    }
    vector<vector<string>> result;
    for (const auto& entry : groups) result.push_back(entry.second);
    return result;
}

int main() {
    vector<string> words = {"eat", "tea", "tan", "ate", "nat", "bat"};
    int i = 0;
    for (const auto& group : group_anagrams(words)) {
        cout << "group " << i++ << " -> ";
        for (const string& word : group) cout << word << " ";
        cout << endl;
    }
    return 0;
}
