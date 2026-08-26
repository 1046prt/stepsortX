// sortsort · Robin Hood Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-robin-hood

#include <bits/stdc++.h>
using namespace std;

const int CAP = 11;

struct Slot {
    int key = 0;
    int dist = -1;  // distance from ideal slot (-1 = empty)
};

class RobinHoodTable {
    vector<Slot> slots;

public:
    RobinHoodTable() : slots(CAP) {}

    int insert(int key) {
        Slot entry{key, 0};
        int pos = key % CAP;
        while (true) {
            Slot cur = slots[pos];
            if (cur.dist == -1) {
                slots[pos] = entry;
                return entry.dist;
            }
            if (cur.key == key) return -1;  // duplicate ignored
            if (cur.dist < entry.dist) swap(entry, slots[pos]);
            entry.dist++;
            pos = (pos + 1) % CAP;
        }
    }

    int search(int key) const {
        int base = key % CAP;
        for (int dist = 0; dist < CAP; dist++) {
            const Slot& cur = slots[(base + dist) % CAP];
            if (cur.dist == -1 || cur.dist < dist) return -1;
            if (cur.key == key) return (base + dist) % CAP;
        }
        return -1;
    }

    const Slot& slotAt(int i) const { return slots[i]; }
};

int main() {
    RobinHoodTable rt;
    int items[] = {10, 20, 30, 42, 52};
    for (int k : items) {
        cout << "insert " << k << " -> settled at distance "
             << rt.insert(k) << endl;
    }
    cout << "table layout:" << endl;
    for (int i = 0; i < CAP; i++) {
        if (rt.slotAt(i).dist == -1) cout << "  slot " << i << " -> (empty)";
        else cout << "  slot " << i << " -> key " << rt.slotAt(i).key
                  << " distance " << rt.slotAt(i).dist;
        cout << endl;
    }
    cout << "search 42 -> slot " << rt.search(42) << endl;
    cout << "search 77 -> slot " << rt.search(77) << " (-1 = absent)" << endl;
    return 0;
}
