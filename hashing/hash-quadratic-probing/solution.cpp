// Stepsort · Quadratic Probing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-quadratic-probing

#include <bits/stdc++.h>
using namespace std;

const int CAP = 11;

int h(int key) { return key % CAP; }

int insert(vector<int>& keys, vector<string>& values, int key,
           const string& value) {
    for (int i = 0; i < CAP; i++) {
        int slot = (h(key) + i * i) % CAP;  // offsets 0, 1, 4, 9, ...
        if (keys[slot] == INT_MIN || keys[slot] == key) {
            keys[slot] = key;
            values[slot] = value;
            return i + 1;
        }
    }
    return -1;
}

int search(const vector<int>& keys, int key) {
    for (int i = 0; i < CAP; i++) {
        int slot = (h(key) + i * i) % CAP;
        if (keys[slot] == INT_MIN) return -1;
        if (keys[slot] == key) return slot;
    }
    return -1;
}

int main() {
    vector<int> keys(CAP, INT_MIN);
    vector<string> values(CAP);
    cout << "insert 10 -> " << insert(keys, values, 10, "A") << " probes" << endl;
    cout << "insert 21 -> " << insert(keys, values, 21, "B") << " probes" << endl;
    cout << "insert 32 -> " << insert(keys, values, 32, "C") << " probes" << endl;
    cout << "insert 43 -> " << insert(keys, values, 43, "D") << " probes" << endl;
    for (int k : {10, 21, 32, 43, 54}) {
        int slot = search(keys, k);
        if (slot == -1) cout << "search " << k << " -> not found" << endl;
        else cout << "search " << k << " -> slot " << slot
                  << " (" << values[slot] << ")" << endl;
    }
    return 0;
}
