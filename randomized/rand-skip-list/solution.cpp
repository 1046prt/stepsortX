// Stepsort · Skip List
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-skip-list

#include <bits/stdc++.h>
using namespace std;

const int MAX_LEVEL = 4;  // cap on tower height
const double P = 0.5;     // probability of growing one extra level

struct Node {
    int key;
    vector<Node*> forward;  // next node at each level
    Node(int key, int level) : key(key), forward(level + 1, nullptr) {}
};

class SkipList {
  public:
    SkipList() : header(new Node(INT_MIN, MAX_LEVEL)), level(0), rng(42) {}

    bool search(int key) {
        Node* node = header;
        for (int i = level; i >= 0; i--)
            while (node->forward[i] && node->forward[i]->key < key)
                node = node->forward[i];
        return node->forward[0] && node->forward[0]->key == key;
    }

    bool insert(int key) {
        vector<Node*> update(MAX_LEVEL + 1, header);
        Node* node = header;
        for (int i = level; i >= 0; i--) {
            while (node->forward[i] && node->forward[i]->key < key)
                node = node->forward[i];
            update[i] = node;
        }
        if (node->forward[0] && node->forward[0]->key == key) return false;
        int lvl = randomLevel();
        level = max(level, lvl);
        Node* created = new Node(key, lvl);
        for (int i = 0; i <= lvl; i++) {
            created->forward[i] = update[i]->forward[i];
            update[i]->forward[i] = created;
        }
        return true;
    }

    bool remove(int key) {
        vector<Node*> update(MAX_LEVEL + 1, header);
        Node* node = header;
        for (int i = level; i >= 0; i--) {
            while (node->forward[i] && node->forward[i]->key < key)
                node = node->forward[i];
            update[i] = node;
        }
        Node* target = node->forward[0];
        if (!target || target->key != key) return false;
        for (int i = 0; i <= level; i++)
            if (update[i]->forward[i] == target)
                update[i]->forward[i] = target->forward[i];
        delete target;
        while (level > 0 && !header->forward[level]) level--;
        return true;
    }

  private:
    int randomLevel() {
        uniform_real_distribution<double> coin(0.0, 1.0);
        int lvl = 0;
        while (coin(rng) < P && lvl < MAX_LEVEL) lvl++;
        return lvl;
    }

    Node* header;
    int level;
    mt19937 rng;
};

int main() {
    SkipList list;
    for (int value : {10, 30, 20, 50, 40}) list.insert(value);
    cout << boolalpha;
    cout << "search 20: " << list.search(20) << endl;
    cout << "search 60: " << list.search(60) << endl;
    list.remove(30);
    cout << "search 30 after removal: " << list.search(30) << endl;
    return 0;
}
