// Stepsort · Consistent Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/consistent-hashing

#include <bits/stdc++.h>
using namespace std;

unsigned int hash_text(const string& text) {
    unsigned int value = 0;
    for (char ch : text) value = value * 131u + (unsigned char)ch;
    value ^= value >> 16;              // avalanche so nearby names spread out
    value *= 0x45d9f3bu;
    value ^= value >> 16;
    value *= 0x45d9f3bu;
    value ^= value >> 16;
    return value;
}

class HashRing {
    vector<pair<unsigned int, string>> entries;  // kept sorted by hash

public:
    void add_server(const string& name) {
        entries.push_back({hash_text(name), name});
        sort(entries.begin(), entries.end());
    }

    string get_server(long long key) const {
        unsigned int kh = hash_text(to_string(key));
        auto it = lower_bound(entries.begin(), entries.end(),
                              make_pair(kh, string()));
        if (it == entries.end()) it = entries.begin();  // wrap around ring
        return it->second;
    }
};

int main() {
    HashRing ring;
    ring.add_server("alpha");
    ring.add_server("bravo");
    ring.add_server("charlie");
    long long keys[] = {101, 202, 303, 404, 505, 606};
    vector<string> before;
    cout << "initial ring:" << endl;
    for (long long k : keys) {
        before.push_back(ring.get_server(k));
        cout << "  key " << k << " -> " << before.back() << endl;
    }
    ring.add_server("delta");
    cout << "after adding delta:" << endl;
    int moved = 0;
    for (int i = 0; i < 6; i++) {
        string now = ring.get_server(keys[i]);
        bool changed = now != before[i];
        if (changed) moved++;
        cout << "  key " << keys[i] << " -> " << now
             << (changed ? " (moved)" : "") << endl;
    }
    cout << "remapped " << moved << " of 6 keys" << endl;
    return 0;
}
