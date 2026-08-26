// Stepsort · Double Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-double-hashing

#include <bits/stdc++.h>
using namespace std;

const int CAP = 11;

int h1(int key) { return key % CAP; }

int h2(int key) { return 1 + (key % (CAP - 1)); }  // never zero

int insert(vector<int>& keys, vector<string>& values, int key,
           const string& value) {
    int base = h1(key), stride = h2(key);
    for (int i = 0; i < CAP; i++) {
        int slot = (base + i * stride) % CAP;
        if (keys[slot] == INT_MIN || keys[slot] == key) {
            keys[slot] = key;
            values[slot] = value;
            return i;
        }
    }
    return -1;
}

int search(const vector<int>& keys, int key) {
    int base = h1(key), stride = h2(key);
    for (int i = 0; i < CAP; i++) {
        int slot = (base + i * stride) % CAP;
        if (keys[slot] == INT_MIN) return -1;
        if (keys[slot] == key) return slot;
    }
    return -1;
}

int main() {
    vector<int> keys(CAP, INT_MIN);
    vector<string> values(CAP);
    int r = insert(keys, values, 10, "A");
    cout << "insert 10 -> stride " << h2(10) << ", attempts " << r + 1 << endl;
    r = insert(keys, values, 21, "B");
    cout << "insert 21 -> stride " << h2(21) << ", attempts " << r + 1 << endl;
    r = insert(keys, values, 32, "C");
    cout << "insert 32 -> stride " << h2(32) << ", attempts " << r + 1 << endl;
    for (int k : {10, 21, 32, 54}) {
        cout << "search " << k << " -> slot " << search(keys, k) << endl;
    }
    return 0;
}
