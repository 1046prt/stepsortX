// Stepsort · Linear Probing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-linear-probing

#include <bits/stdc++.h>
using namespace std;

const int CAP = 11;
const int EMPTY = INT_MIN;
const int DELETED = INT_MIN + 1;  // tombstone

struct Table {
    vector<int> keys = vector<int>(CAP, EMPTY);
    vector<string> values = vector<string>(CAP);

    int insert(int key, const string& value) {
        int first_dead = -1;
        for (int step = 0; step < CAP; step++) {
            int slot = (key % CAP + step) % CAP;
            if (keys[slot] == EMPTY) {
                int target = (first_dead == -1) ? slot : first_dead;
                keys[target] = key;
                values[target] = value;
                return step + 1;  // probes used
            }
            if (keys[slot] == DELETED) {
                if (first_dead == -1) first_dead = slot;
            } else if (keys[slot] == key) {
                values[slot] = value;
                return step + 1;
            }
        }
        return -1;
    }

    int find(int key) const {
        for (int step = 0; step < CAP; step++) {
            int slot = (key % CAP + step) % CAP;
            if (keys[slot] == EMPTY) return -1;
            if (keys[slot] == key) return slot;
        }
        return -1;
    }

    bool erase(int key) {
        int slot = find(key);
        if (slot == -1) return false;
        keys[slot] = DELETED;
        return true;
    }

    void dump() const {
        cout << "slots:";
        for (int i = 0; i < CAP; i++) {
            if (keys[i] == EMPTY) cout << " _";
            else if (keys[i] == DELETED) cout << " #";
            else cout << " " << keys[i];
        }
        cout << endl;
    }
};

int main() {
    Table t;
    cout << "insert 22 -> " << t.insert(22, "V") << " probes" << endl;
    cout << "insert 33 -> " << t.insert(33, "G") << " probes" << endl;
    cout << "insert 44 -> " << t.insert(44, "S") << " probes" << endl;
    int slot = t.find(33);
    cout << "search 33 -> slot " << slot << " value " << t.values[slot] << endl;
    cout << "delete 33 -> " << boolalpha << t.erase(33) << endl;
    cout << "search 33 after delete -> slot " << t.find(33) << endl;
    cout << "insert 55 -> " << t.insert(55, "F")
         << " probes (tombstone reused)" << endl;
    t.dump();
    return 0;
}
