// sortsort · Chaining
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-chaining

#include <bits/stdc++.h>
using namespace std;

class ChainHashTable {
    vector<vector<pair<int, string>>> buckets;
    int capacity;

    int index_of(int key) const { return key % capacity; }

public:
    explicit ChainHashTable(int cap) : buckets(cap), capacity(cap) {}

    void insert(int key, const string& value) {
        auto& chain = buckets[index_of(key)];
        for (auto& item : chain) {
            if (item.first == key) {
                item.second = value;
                return;
            }
        }
        chain.push_back(make_pair(key, value));
    }

    string search(int key) const {
        for (const auto& item : buckets[index_of(key)]) {
            if (item.first == key) return item.second;
        }
        return "";
    }

    bool remove(int key) {
        auto& chain = buckets[index_of(key)];
        for (auto it = chain.begin(); it != chain.end(); ++it) {
            if (it->first == key) {
                chain.erase(it);
                return true;
            }
        }
        return false;
    }

    void print_table() const {
        for (int i = 0; i < capacity; i++) {
            cout << "bucket " << i << " ->";
            for (const auto& item : buckets[i]) {
                cout << " (" << item.first << "," << item.second << ")";
            }
            if (buckets[i].empty()) cout << " (empty)";
            cout << endl;
        }
    }
};

int main() {
    ChainHashTable ht(7);
    ht.insert(10, "A");
    ht.insert(17, "B");  // 10 and 17 share bucket 3
    ht.insert(24, "C");
    ht.insert(5, "D");
    string hit = ht.search(17);
    cout << "search 17 -> " << (hit.empty() ? "not found" : hit) << endl;
    cout << "search 99 -> "
         << (ht.search(99).empty() ? "not found" : "found") << endl;
    cout << "delete 17 -> " << boolalpha << ht.remove(17) << endl;
    cout << "delete 99 -> " << ht.remove(99) << endl;
    cout << "table state:" << endl;
    ht.print_table();
    return 0;
}
