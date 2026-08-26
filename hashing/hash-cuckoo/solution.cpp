// Stepsort · Cuckoo Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-cuckoo

#include <bits/stdc++.h>
using namespace std;

class CuckooTable {
    vector<int> t1, t2;
    int cap;

public:
    explicit CuckooTable(int c = 4) : t1(c, -1), t2(c, -1), cap(c) {}

    int h1(int key) const { return key % cap; }
    int h2(int key) const { return (key / cap) % cap; }

    bool lookup(int key) const {
        return t1[h1(key)] == key || t2[h2(key)] == key;
    }

    void insert(int key) {
        const int max_kicks = 8;
        for (int round = 0; round < max_kicks; round++) {
            int pos = h1(key);
            if (t1[pos] == -1) { t1[pos] = key; return; }
            swap(key, t1[pos]);  // evict the occupant
            cout << "  kick " << key << " out of T1 slot " << pos << endl;
            pos = h2(key);
            if (t2[pos] == -1) { t2[pos] = key; return; }
            swap(key, t2[pos]);
            cout << "  kick " << key << " out of T2 slot " << pos << endl;
        }
        rehash();
        insert(key);
    }

private:
    void rehash() {
        vector<int> old;
        for (int k : t1) if (k != -1) old.push_back(k);
        for (int k : t2) if (k != -1) old.push_back(k);
        cap *= 2;
        t1.assign(cap, -1);
        t2.assign(cap, -1);
        cout << "  rehash with capacity " << cap << endl;
        for (int k : old) insert(k);
    }
};

int main() {
    CuckooTable ct(4);
    int items[] = {4, 8, 12, 1, 5};
    for (int k : items) {
        cout << "insert " << k << endl;
        ct.insert(k);
    }
    for (int k : {4, 8, 12, 1, 5, 99}) {
        cout << boolalpha << "lookup " << k << " -> " << ct.lookup(k) << endl;
    }
    return 0;
}
